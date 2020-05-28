package com.jelly.thor.commonutils.base.rv

import androidx.annotation.LayoutRes

/**
 * 类描述：多布局支持<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2020/2/28 14:33 <br/>
 */
interface MultiTypeSupport<T> {
    /**
     * 根据当前位置或者条目数据返回布局
     */
    @LayoutRes
    fun getLayoutId(item: T?, position: Int): Int
}

