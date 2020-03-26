package com.jelly.thor.commonutils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import android.widget.TextView
import com.jelly.thor.commonutils.annotation.Style
import com.jelly.thor.commonutils.bean.TextMoreStyle

/**
 * 类描述：textView扩展方法 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/6/24 13:47 <br/>
 */
/*enum class FontStyleEnum {
    GT_WALSHEIM_BOLD,
    GT_WALSHEIM_REGULAR,
    GT_WALSHEIM_LIGHT
}

//main\assets
//main\res\font
private val GT_WALSHEIM_BOLD = Typeface.createFromAsset(applicationContext.assets, "fonts/gt_walsheim_bold.ttf")
        ?: throw IllegalArgumentException("检查字体保存路径是否有：fonts/gt_walsheim_bold.ttf")

private val GT_WALSHEIM_REGULAR = Typeface.createFromAsset(applicationContext.assets, "fonts/gt_walsheim_regular.ttf")
        ?: throw IllegalArgumentException("检查字体保存路径是否有：fonts/gt_walsheim_regular.ttf")

private val GT_WALSHEIM_LIGHT = Typeface.createFromAsset(applicationContext.assets, "fonts/gt_walsheim_light.ttf")
        ?: throw IllegalArgumentException("检查字体保存路径是否有：fonts/gt_walsheim_light.ttf")

*/
/**
 * 设置字体样式，默认不加粗
 *//*
@JvmOverloads
fun TextView?.setFontStyle(fontStyle: FontStyleEnum, isBold: Boolean = false) {
    if (this == null) {
        return
    }
    val typeface = getTypeface(fontStyle)
    this.typeface = typeface
    if (isBold) {
        this.paint.isFakeBoldText = true
    }
}

fun getTypeface(fontStyle: FontStyleEnum): Typeface {
    return when (fontStyle) {
        FontStyleEnum.GT_WALSHEIM_BOLD -> GT_WALSHEIM_BOLD
        FontStyleEnum.GT_WALSHEIM_REGULAR -> GT_WALSHEIM_REGULAR
        FontStyleEnum.GT_WALSHEIM_LIGHT -> GT_WALSHEIM_LIGHT
    }
}*/

/**
 * 设置字体样式
 */
fun TextView?.setMoreStyle(
    vararg itemArray: TextMoreStyle?
) {
    if (this == null) {
        return
    }
    if (itemArray.isEmpty()) {
        this.text = ""
        return
    }
    val ssb = SpannableStringBuilder()
    itemArray.forEachIndexed { index, textBoldStyle ->
        textBoldStyle?.let {
            ssb.append(it.textStr)
            val startI = if (index == 0) {
                0
            } else {
                ssb.length - it.textStr.length
            }

            //设置颜色
            if (it.color != -1) {
                ssb.setSpan(
                    ForegroundColorSpan(it.color),
                    startI,
                    ssb.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }

            //设置粗细
            if (it.style != Style.NO_SET) {
                ssb.setSpan(
                    StyleSpan(it.style),
                    startI,
                    ssb.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }

            //设置字体大小
            if (it.textSize != -1) {
                ssb.setSpan(
                    AbsoluteSizeSpan(it.textSize),
                    startI,
                    ssb.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
    this.text = ssb
}

/**
 * 通过defaultFromStyle设置textView字体typeface
 */
fun TextView?.setDefaultFromStyle(typefaceType: Int = Typeface.BOLD) {
    if (this == null) {
        return
    }
    this.typeface = Typeface.defaultFromStyle(typefaceType)
}

/**
 * textView显示全部文字
 */
fun TextView?.setShowAllText() {
    if (this == null) {
        return
    }
    this.post {
        val layout = this.layout
        if (layout != null) {
            if (layout.getEllipsisCount(this.lineCount - 1) > 0) {
                //Log.d("123===", "Text is ellipsized");
                //测量字符串的长度
                val measureWidth = this.paint.measureText(this.text.toString())
                //得到TextView 的宽度
                val width = this.width - this.paddingLeft - this.paddingRight
                //当前size大小
                var textSize = this.textSize
                if (width < measureWidth) {
                    textSize *= width / measureWidth
                }
                if (textSize < 21) {
                    //注意，使用像素大小设置
                    this.setTextSize(TypedValue.COMPLEX_UNIT_PX, 21f)
                } else {
                    //注意，使用像素大小设置
                    this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
                }
            }/* else {
                     //Log.d("123===", "Text is not ellipsized");
                }*/
        }
    }
}