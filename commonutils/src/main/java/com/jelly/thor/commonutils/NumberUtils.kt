package com.jelly.thor.commonutils

import java.math.BigDecimal

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
    fun add(v1: Double, v2: Double): Double {
        val b1 = BigDecimal(java.lang.Double.toString(v1))
        val b2 = BigDecimal(java.lang.Double.toString(v2))
        return b1.add(b2).toDouble()
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    @JvmStatic
    fun add(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.add(b2).toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    @JvmStatic
    fun sub(v1: Double, v2: Double): Double {
        val b1 = BigDecimal(java.lang.Double.toString(v1))
        val b2 = BigDecimal(java.lang.Double.toString(v2))
        return b1.subtract(b2).toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    @JvmStatic
    fun sub(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.subtract(b2).toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    @JvmStatic
    fun mul(v1: Double, v2: Double): Double {
        val b1 = BigDecimal(java.lang.Double.toString(v1))
        val b2 = BigDecimal(java.lang.Double.toString(v2))
        return b1.multiply(b2).toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    @JvmStatic
    fun mul(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.multiply(b2).toDouble()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    @JvmStatic
    fun div(v1: Double, v2: Double, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException(
                    "保留位数必需是0或正整数")
        }
        if (v2 == 0.0) {
            throw IllegalArgumentException(
                    "除数必需非0")
        }
        val b1 = BigDecimal(java.lang.Double.toString(v1))
        val b2 = BigDecimal(java.lang.Double.toString(v2))
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    @JvmStatic
    fun div(v1: String, v2: String, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException(
                    "保留位数必需是0或正整数")
        }
        if (v2 == "0" || v2.trim() == "") {
            throw IllegalArgumentException(
                    "除数必需非0")
        }
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }
}
