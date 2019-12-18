package com.jelly.thor.commonutils.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.jelly.thor.commonutils.ActivityUtils
import com.jelly.thor.commonutils.BuildConfig

/**
 * 类描述：基础Activity<br></br>
 * 创建人：吴冬冬<br></br>
 * 创建时间：2018/4/12 17:44 <br></br>
 */
abstract class BaseActivity : AppCompatActivity() {
    private val TAG = "BaseActivity"

    /**
     * 设置布局
     * @return R.layout.xxx
     */
    @get:LayoutRes
    protected abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "--------------------------------------------------------------------")
            Log.d(TAG, "当前Activity：" + this.javaClass.simpleName + "---->>>onCreate()")
            Log.d(TAG, "--------------------------------------------------------------------")
        }

        //设置自定义的屏幕密度
        ActivityUtils.setCustomDensity(this, application)

        //设置布局
        setContentView(contentView)

        //初始化p层，只有mvp模式才需要
        //initPresenter();

        //初始化界面
        initView()

        //初始化头部
        initTitle()

        //初始化点击事件，如果用注解注入方式的就不需要该方法
        initEvent()

        //初始化数据
        initData()
    }

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 初始化点击事件，如果用注解注入方式的就不需要改方法
     */
    protected abstract fun initEvent()

    /**
     * 初始化头部
     */
    protected abstract fun initTitle()

    /**
     * 初始化界面，如果是kt代码不需要初始化view
     */
    protected abstract fun initView()

    /**
     * 启动activity，如果需要传餐就需要在要跳转的类中自己写一个静态方法跳转到该类
     * @param clazz
     */
    protected fun startActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    //    /**
    //     * 启动activity，如果需要传餐就需要在要跳转的类中自己写一个静态方法跳转到该类
    //     * @param clazz
    //     */
    //    不经常用的不需要写到里面影响性能
    //    protected void startActivityForResult(Class<?> clazz, int requestCode){
    //        Intent intent = new Intent(this, clazz);
    //        startActivityForResult(intent, requestCode);
    //    }
}