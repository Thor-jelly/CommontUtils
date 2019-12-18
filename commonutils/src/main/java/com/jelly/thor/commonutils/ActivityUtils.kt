package com.jelly.thor.commonutils

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.NotNull


/**
 * 类描述：activity管理类 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/12/27 09:31 <br/>
 */
object ActivityUtils{
    /**
     * 所有打开的Activity
     */
    private val mActivityList =  mutableListOf<AppCompatActivity>()

    /**
     * 添加统一管理
     */
    @Synchronized
    @JvmStatic
    fun attach(@NotNull activity: AppCompatActivity) {
        mActivityList.indexOf(activity).takeIf {
            it == -1;
        }?.let {
            mActivityList.add(activity)
        }
    }

    /**
     * 移除解绑 - 防止内存泄漏
     */
    @Synchronized
    @JvmStatic
    fun detach(@NotNull detachActivity: AppCompatActivity) {
        mActivityList.indexOf(detachActivity).takeIf {
            it > -1
        }?.let {
            mActivityList.remove(detachActivity)
        }
    }

    /**
     * 根据Activity的类名关闭 Activity
     */
    @JvmStatic
    fun finish(activityClass: Class<out AppCompatActivity>) {
        val iterator = mActivityList.iterator()
        for (activity in iterator) {
            //如果遍历删除有问题，可以参考EventBus取消注册的方法
            activity.takeIf {
                //getCanonicalName 可以获取对应包名下的activity 这样可以防止关闭不同包下相同名称的activity
                it::class.java.canonicalName == activityClass.canonicalName
            }?.let {
                it.finish()
                iterator.remove()
            }
        }
    }

    /**
     * 退出整个应用
     */
    @JvmStatic
    fun exit() {
        for (activity in mActivityList) {
            activity.finish()
        }
        mActivityList.clear()
        // 杀死该应用进程
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }

    /**
     * 获取当前的Activity（最前面）
     */
    @JvmStatic
    fun getCurrentActivity(): AppCompatActivity? {
        return if (mActivityList.isEmpty()) {
            null
        } else mActivityList[mActivityList.lastIndex]
    }


    private var sComponentDensity: Float = 0F
    private var sComponentScaledDensity: Float = 0F
    /**
     * 还未调试过
     * 设置自定义的屏幕密度
     * 在BaseActivity中的onCreate()方法中调用
     */
    fun setCustomDensity(activity: AppCompatActivity, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics

        if (sComponentDensity == 0f) {
            sComponentDensity = appDisplayMetrics.density
            sComponentScaledDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sComponentScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }
            })
        }

        val targetDensity = appDisplayMetrics.widthPixels / 360f//以360dp(360\*640)和宽维度来适配的
        val targetScaledDensity = targetDensity * (sComponentScaledDensity / sComponentDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()

        //赋值到application中的density中
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        //下面赋值到activity中的density中
        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi
    }
}