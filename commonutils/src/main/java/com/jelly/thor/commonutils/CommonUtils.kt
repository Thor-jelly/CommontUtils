package com.jelly.thor.commonutils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.hardware.Camera
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * 类描述：通用方法<br></br>
 * 创建人：吴冬冬<br></br>
 * 创建时间：2018/5/30 18:41 <br></br>
 */
object CommonUtils {
    /**
     * 关闭软键盘
     */
    @JvmStatic
    fun closeKeyboard(activity: Activity, vararg editText: EditText) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        for (ed in editText) {
            if (imm?.isActive(ed) == true) {  //i(imm.isActive())  //一直是true
                //imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//显示或隐藏软键盘
                imm.hideSoftInputFromWindow(ed.windowToken, 0)//隐藏软键盘
                //imm.showSoftInput(myEditText, 0);//显示软键盘
                /* mOrderTimeLl.requestFocus();
                mOrderTimeLl.setFocusableInTouchMode(true);*/
                ed.clearFocus()
                ed.isSelected = false
            }
        }
    }

    /**
     * 显示软键盘
     */
    @JvmStatic
    fun showKeyboard(activity: Activity, editText: EditText) {
        editText.requestFocus()
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(editText, 0)
    }

    /**
     * 自定义日期转换，秒传入
     * 不具有四舍五入功能
     *
     * @param rule yyyy-MM-dd HH:mm:ss
     */
    @JvmStatic
    fun getSTime(time: String?, rule: String): String {
        if (time != null) {
            val longTime = time.toLong()
            val date = Date(longTime * 1000)
            @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat(rule)
            return format.format(date)
        }
        return "0"
    }

    /**
     * 自定义日期转换，毫秒传入
     *
     * @param rule yyyy-MM-dd HH:mm:ss
     */
    @JvmStatic
    fun getMSTime(time: String?, rule: String): String {
        if (time != null) {
            val longTime = time.toLong()
            val date = Date(longTime)
            @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat(rule)
            return format.format(date)
        }
        return "0"
    }

    /**
     * 是否有摄像头
     */
    @JvmStatic
    fun hasCamera(): Boolean {
        //PackageManager pm = GetApplication.get().getApplicationContext().getPackageManager();
        //pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)--pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
        if (Camera.getNumberOfCameras() > 0) {
            //Log.d("123===", "具有摄像头！")
            return true
        }
        //Log.d("123===", "不具有摄像头！")
        return false
    }
}
