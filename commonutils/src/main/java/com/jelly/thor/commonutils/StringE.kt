package com.jelly.thor.commonutils

import com.jelly.thor.commonutils.bean.TextMoreStyle
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * 类描述：String扩展类<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/6/25 14:11 <br/>
 */

fun String?.parseInt(): Int {
    if (this.isNullOrEmpty()) {
        return 0
    }
    return try {
        this.toInt()
    } catch (e: Exception) {
        //Log.w("123===", this + "-->String转换Int异常=" + e.message)
        0
    }
}

fun String?.parseDouble(): Double {
    if (this.isNullOrEmpty()) {
        return 0.0
    }
    return try {
        this.toDouble()
    } catch (e: Exception) {
        //Log.w("123===", this + "-->String转换Int异常=" + e.message)
        0.0
    }
}

/**
 * 格式化数字升级版
 * @param dot 保留小数点位数
 * @param isShowEnd0 是否显示末尾0
 * @param roundingMode 四舍五入模式
 */
@JvmOverloads
fun String?.formatNumber(
    dot: Int,
    isShowEnd0: Boolean = false,
    roundingMode: RoundingMode = RoundingMode.FLOOR
): String {
    val patternSb = StringBuilder("0")
    for (i in 0 until dot) {
        if (i == 0) {
            patternSb.append(".")
        }
        if (isShowEnd0) {
            patternSb.append("0")
        } else {
            patternSb.append("#")
        }
    }
    return this.formatNumber(patternSb.toString(), roundingMode)
}

/**
 * 格式化数字
 * ##0.000
 * @param roundingMode 四舍五入模式
 */
@JvmOverloads
fun String?.formatNumber(
    formatStr: String,
    roundingMode: RoundingMode = RoundingMode.FLOOR
): String {
    val df = DecimalFormat(formatStr)
    df.roundingMode = roundingMode
    if (this.isNullOrEmpty()) {
        return df.format(0)
    }
    val numberD: Double
    try {
        numberD = this.toDouble()
    } catch (e: Exception) {
        return df.format(0)
    }
    return df.format(numberD)
}

/**
 * string 转换为中英文数组 设置到富文本中
 */
fun String?.toZhEnSpan(zhTextSizePx: Int, enTextSizePx: Int): Array<TextMoreStyle> {
    if (this.isNullOrEmpty()) {
        return arrayOf()
    }
    val enStr =
        "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^&*()-=_+`~[]{}\\|;':\",./<>?"
    val result = mutableListOf<TextMoreStyle>()
    val sb = StringBuilder()
    //0：正在遍历中文；1：正在遍历英文
    var oldForType = 0
    val toCharArray = this.toCharArray()
    for (indexValue in toCharArray.withIndex()) {
        val c = indexValue.value
        val oldIsEn = oldForType != 0
        if (enStr.contains(c)) {
            //当前字母是英文
            if (oldIsEn) {
                //原来是英文
                sb.append(c)
                if (indexValue.index == toCharArray.size - 1) {
                    addZhEnSpanType2Array(sb, enTextSizePx, result)
                }
            } else {
                //原来是中文
                addZhEnSpanType2Array(sb, zhTextSizePx, result)
                sb.append(c)
                oldForType = 1
            }
        } else {
            //当前字母是中文
            if (oldIsEn) {
                //原来是英文情况
                addZhEnSpanType2Array(sb, enTextSizePx, result)
                sb.append(c)
                oldForType = 0
            } else {
                //原来是中文
                sb.append(c)
                if (indexValue.index == toCharArray.size - 1) {
                    addZhEnSpanType2Array(sb, zhTextSizePx, result)
                }
            }
        }
    }
    return result.toTypedArray()
}

/**
 * 添加中英文字体大小富文本 进入数组
 */
private fun addZhEnSpanType2Array(
    sb: StringBuilder,
    zhTextSizePx: Int,
    result: MutableList<TextMoreStyle>
) {
    val sbStr = sb.toString()
    if (sbStr.isNotEmpty()) {
        val zhTextMoreStyle = TextMoreStyle.builder(sbStr)
            .setTextSize(zhTextSizePx)
            .build()
        result.add(zhTextMoreStyle)
        sb.setLength(0)
    }
}