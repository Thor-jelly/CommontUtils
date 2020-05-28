package com.jelly.thor.commonutils.base.rv

import com.jelly.thor.commonutils.R

/**
 * 类描述：没有数据类型 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/9/20 17:23 <br/>
 */
abstract class NoDataTypeSealed(val id: Int, val imageResourceId: Int)

//参考样式
class NoDataTypeEnumE private constructor(id: Int, imageResourceId: Int) :
    NoDataTypeSealed(id, imageResourceId) {
    companion object {
        /**
         * 没有该类型
         */
        @JvmField
        val NULL = NoDataTypeEnumE(-1, R.drawable.ic_launcher_round)

        /**
         * 没有数据
         */
        @JvmField
        val NO_DATA = NoDataTypeEnumE(1, R.drawable.no_data_img)

        /**
         * 没有网络
         */
        @JvmField
        val NO_NET = NoDataTypeEnumE(2, R.drawable.no_net_img)
    }
}