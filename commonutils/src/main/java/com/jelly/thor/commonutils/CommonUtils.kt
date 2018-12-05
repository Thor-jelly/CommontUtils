package com.jelly.thor.commonutils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
//    /**
//     * 将px值转换为dip或dp值，保证尺寸大小不变
//     */
//    fun px2dp(context: Context, pxValue: Float): Int {
//        val scale = context.resources.displayMetrics.density
//        return (pxValue / scale + 0.5f).toInt()
//    }

    /**
     * dp值转换为px值，保证尺寸大小不变
     */
    fun dp2px(context: Context, value: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    fun sp2px(context: Context, value: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.resources.displayMetrics).toInt()
    }

    /**
     * 关闭软键盘
     */
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
    fun showKeyboard(activity: Activity, editText: EditText) {
        editText.requestFocus()
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(editText, 0)
    }

    /**
     * 格式化数字
     *
     * @param rule 规则：如1：#,###.00 保留两位小数；2：#,###.## 如果末尾不为零保留2位，为零省略小数部分
     */
    fun getRuleDecimalFormat(num: String, rule: String): String {
        val decimalFormat = DecimalFormat(rule)
        return decimalFormat.format(num)
    }

    /**
     * 自定义日期转换，秒传入
     * 不具有四舍五入功能
     *
     * @param rule yyyy-MM-dd HH:mm:ss
     */
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
    fun getMSTime(time: String?, rule: String): String {
        if (time != null) {
            val longTime = time.toLong()
            val date = Date(longTime)
            @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat(rule)
            return format.format(date)
        }
        return "0"
    }
}
