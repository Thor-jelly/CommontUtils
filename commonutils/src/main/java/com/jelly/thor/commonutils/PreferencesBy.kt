package com.jelly.thor.commonutils

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 类描述：sharedPreferences属性依赖<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/9/20 14:00 <br/>
 */
class PreferencesBy<T>(private val context: Context, private val key: String = "", private val defaultValue: T, private val sharedPreferencesName: String = "sharedPreferencesName") : ReadWriteProperty<Any, T> {
    private val sharedPreferences by lazy {
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }


    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val newKey = if (key.isEmpty()) property.name else key
        return getSharedPreferences(newKey, defaultValue)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    private fun getSharedPreferences(key: String, defaultValue: T): T {
        return sharedPreferences.run {
            when (defaultValue) {
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                is String -> getString(key, defaultValue)
                else -> throw IllegalArgumentException("不支持该类型")
            }
        } as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val newKey = if (key.isEmpty()) property.name else key
        putSharedPreferencesValue(newKey, value)
    }

    private fun putSharedPreferencesValue(key: String, value: T) {
        val edit = sharedPreferences.edit()
        edit.apply {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("不支持该类型")
            }
        }.apply()
    }
}