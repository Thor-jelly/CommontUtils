package com.jelly.thor.commonutils

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import com.jelly.thor.commonutils.annotation.Visibility

/**
 * 类描述：view工具类 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/12/5 14:39 <br/>
 */
object ViewUtils {
    /**
     * 判断是否为一种显示方式
     *
     * @param view     需要改变显示方式的View
     * @param showType 改为什么显示方式，View.VISIBLE、View.GONE、View.INVISIBLE
     */
    @JvmStatic
    fun isShowType(@NonNull view: View, @Visibility showType: Int): Boolean {
        return view.visibility == showType
    }

    /**
     * 设置显示方式
     *
     * @param view     需要改变显示方式的View
     * @param showType 改为什么显示方式，View.VISIBLE、View.GONE、View.INVISIBLE
     */
    @JvmStatic
    fun setShowModel(@NonNull view: View, @Visibility showType: Int) {
        when (showType) {
            View.VISIBLE -> if (view.visibility != View.VISIBLE) {
                view.visibility = View.VISIBLE
            }
            View.GONE -> if (view.visibility != View.GONE) {
                view.visibility = View.GONE
            }
            View.INVISIBLE -> if (view.visibility != View.INVISIBLE) {
                view.visibility = View.INVISIBLE
            }
            else -> throw IllegalArgumentException("setShowType, 传入非法参数")
        }
    }

    /**
     * textView显示全部文字
     */
    @JvmStatic
    fun setShowAllText(tv: TextView) {
        tv.post {
            val layout = tv.layout
            if (layout != null) {
                if (layout.getEllipsisCount(tv.lineCount - 1) > 0) {
                    //Log.d("123===", "Text is ellipsized");
                    //测量字符串的长度
                    val measureWidth = tv.paint.measureText(tv.text.toString())
                    //得到TextView 的宽度
                    val width = tv.width - tv.paddingLeft - tv.paddingRight
                    //当前size大小
                    var textSize = tv.textSize
                    if (width < measureWidth) {
                        textSize *= width / measureWidth
                    }
                    if (textSize < 21) {
                        //注意，使用像素大小设置
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 21f)
                    } else {
                        //注意，使用像素大小设置
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
                    }
                }/* else {
                     //Log.d("123===", "Text is not ellipsized");
                }*/
            }
        }
    }
}