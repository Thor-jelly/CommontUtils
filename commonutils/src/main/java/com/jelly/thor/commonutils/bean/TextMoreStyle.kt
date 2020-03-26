package com.jelly.thor.commonutils.bean

import com.jelly.thor.commonutils.annotation.Style


/**
 * 类描述：textView 是否加粗样式<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/10/15 15:12 <br/>
 */
class TextMoreStyle private constructor(builder: Builder) {
    var textStr: String = builder.textStr
        private set

    var textSize: Int = builder.textSize
        private set

    var color: Int = builder.color
        private set

    @Style
    var style: Int = builder.style
        private set



    companion object {
        @JvmStatic
        fun builder(textStr: String): Builder {
            return Builder(textStr)
        }
    }

    class Builder(textStr: String) {
        var textStr: String = ""
            private set

        /**
         * 颜色设置为-1，不设置
         */
        var color: Int = -1
            private set

        /**
         * 字体设置为-1  不设置
         */
        var textSize: Int = -1
            private set

        /**
         * 字体粗细样式
         */
        @Style
        var style: Int = Style.NO_SET
            private set

        init {
            this.textStr = textStr
        }

        /**
         * 传入px
         */
        fun setTextSize(textSize: Int): Builder {
            this.textSize = textSize
            return this
        }

        fun setTextColor(color: Int): Builder {
            this.color = color
            return this
        }

        fun setTextStyle(@Style style: Int): Builder {
            this.style = style
            return this
        }

        fun build(): TextMoreStyle {
            return TextMoreStyle(this)
        }
    }
}

