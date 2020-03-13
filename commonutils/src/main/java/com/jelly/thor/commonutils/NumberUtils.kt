package com.jelly.thor.commonutils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 类描述：算术处理<br></br>
 * 创建人：吴冬冬<br></br>
 * 创建时间：2018/5/30 18:41 <br></br>
 */
object NumberUtils {
    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    @JvmStatic
    fun add(v1: Double, vararg v2: Double): Double {
        val b1 = BigDecimal(v1.toString())
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d.toString())
            all = all.add(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    @JvmStatic
    fun add(v1: String, vararg v2: String): Double {
        val b1 = BigDecimal(v1)
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d)
            all = all.add(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    @JvmStatic
    fun sub(v1: Double, vararg v2: Double): Double {
        val b1 = BigDecimal(v1.toString())
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d.toString())
            all = all.subtract(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    @JvmStatic
    fun sub(v1: String, vararg v2: String): Double {
        val b1 = BigDecimal(v1)
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d)
            all = all.subtract(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    @JvmStatic
    fun mul(v1: Double, vararg v2: Double): Double {
        val b1 = BigDecimal(v1.toString())
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d.toString())
            all = all.multiply(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    @JvmStatic
    fun mul(v1: String, vararg v2: String): Double {
        val b1 = BigDecimal(v1)
        var all = b1
        for (d in v2) {
            val b2 = BigDecimal(d)
            all = all.multiply(b2)
        }
        return all.toDouble()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @param roundingMode 表示舍入模式
     * RoundingMode.CEILING：取右边最近的整数
     * RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
     * RoundingMode.FLOOR：取左边最近的正数
     * RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
     * RoundingMode.HALF_UP:四舍五入，负数原理同上
     * RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
     * @return 两个参数的商
     */
    @JvmStatic
    @JvmOverloads
    fun div(
        v1: Double,
        scale: Int = 2,
        roundingMode: RoundingMode = RoundingMode.HALF_UP,
        vararg v2: Double
    ): Double {
        if (scale < 0) {
            throw IllegalArgumentException(
                "保留位数必需是0或正整数"
            )
        }
        if (v2.isEmpty()) {
            throw IllegalArgumentException("除数不能没传")
        }
        val b1 = BigDecimal(v1.toString())
        var all = b1
        for (d in v2) {
            if (d == 0.0) {
                throw IllegalArgumentException(
                    "除数必需非0"
                )
            }
            val b2 = BigDecimal(d.toString())
            all = all.divide(b2, scale, roundingMode)
        }
        return all.toDouble()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * RoundingMode.CEILING：取右边最近的整数
     * RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
     * RoundingMode.FLOOR：取左边最近的正数
     * RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
     * RoundingMode.HALF_UP:四舍五入，负数原理同上
     * RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
     * @return 两个参数的商
     */
    @JvmStatic
    @JvmOverloads
    fun div(
        v1: String,
        scale: Int = 2,
        roundingMode: RoundingMode = RoundingMode.HALF_UP,
        vararg v2: String
    ): Double {
        if (scale < 0) {
            throw IllegalArgumentException(
                "保留位数必需是0或正整数"
            )
        }
        if (v2.isEmpty()) {
            throw IllegalArgumentException("除数不能没传")
        }
        val b1 = BigDecimal(v1)
        var all = b1
        for (d in v2) {
            if (d == "0" || d.trim() == "") {
                throw IllegalArgumentException(
                    "除数必需非0"
                )
            }
            val b2 = BigDecimal(d)
            all = all.divide(b2, scale, roundingMode)
        }
        return all.toDouble()
    }
}