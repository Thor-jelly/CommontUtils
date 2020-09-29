package com.jelly.thor.commonutils

import android.view.View
import android.view.ViewGroup
import com.jelly.thor.commonutils.annotation.Visibility
import kotlin.math.roundToInt

/**
 * 类描述：view的扩展方法<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/6/26 10:05 <br/>
 */

/**
 * view的显示是否等同于判断的
 */
fun View?.isEqualsVisibility(@Visibility visibility: Int): Boolean {
    if (this == null) {
        return false
    }
    return visibility == this.visibility
}

/**
 * 设置view的显示模式
 */
fun View?.setNewVisibility(@Visibility visibility: Int) {
    if (this == null) {
        return
    }
    if (visibility == this.visibility) {
        return
    }
    this.visibility = visibility
}

/**
 * 当前缩放view必须用一个父布局包裹
 */
fun View?.scaleView(
        isFullView: Boolean = false
) {
    if (this == null) {
        return
    }

    val parentView =
        this.parent as ViewGroup? ?: throw IllegalArgumentException("当前缩放view必须用一个父布局包裹")

    //放大view外侧view的父布局
    val parentPView = parentView.parent as ViewGroup? ?: return

    val childViewHeight = this.measuredHeight
    if (childViewHeight == 0) {
        return
    }
    val childViewWidth = this.measuredWidth
    if (childViewWidth == 0) {
        return
    }

    val newXI = parentPView.width
    if (newXI == 0) {
        return
    }
    val newYI = newXI * childViewHeight / childViewWidth

    val scaleX = newXI / childViewWidth.toFloat()
    val scaleY = newYI / childViewHeight.toFloat()

    val setFullViewHeight: Int
    if (isFullView) {
        //放大view外层view在其父布局位置
        val parentPViewIndex = parentPView.indexOfChild(parentView)
        //放大view外层view 其父布局一共有多少子view
        val allParentChildCount = parentPView.childCount
        //不是放大view的高度
        var noScaleViewHeight = 0
        if (parentPViewIndex > 0) {
            for (i in 0 until allParentChildCount) {
                if (i == parentPViewIndex) {
                    continue
                }
                noScaleViewHeight += parentPView.getChildAt(i).height
            }
        }

        setFullViewHeight = parentPView.height - noScaleViewHeight

        val newChildViewHeight = setFullViewHeight / scaleY
        this.layoutParams.height = newChildViewHeight.roundToInt()
    } else {
        setFullViewHeight = ViewGroup.LayoutParams.MATCH_PARENT
    }

    this.scaleX = scaleX
    this.scaleY = scaleY

    val layoutParams = parentView.layoutParams
    layoutParams.width = newXI
    layoutParams.height = if (isFullView) {
        setFullViewHeight
    } else {
        newYI
    }
    parentView.layoutParams = layoutParams
}