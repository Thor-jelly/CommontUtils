package com.jelly.thor.commonutils

import android.content.Context
import android.util.TypedValue

/**
 * 类描述：Int扩展 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/6/26 11:08 <br/>
 */
fun Int.dp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics).toInt()
}

fun Int.sp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context.resources.displayMetrics).toInt()
}