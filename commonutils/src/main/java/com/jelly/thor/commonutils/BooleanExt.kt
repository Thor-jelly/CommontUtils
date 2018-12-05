package com.jelly.thor.commonutils

/**
 * 类描述：boolean扩展<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/11/15 15:04 <br/>
 */
sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) =
        when {
            this -> {
                WithData(block())
            }
            else -> {
                Otherwise
            }
        }

inline fun <T> Boolean.no(block: () -> T) =
        when {
            this -> {
                Otherwise
            }
            else -> {
                WithData(block())
            }
        }

inline fun <T> BooleanExt<T>.otherwise(block: () -> T) =
        when (this) {
            is Otherwise -> block()
            is WithData -> this.data
        }