package com.jelly.thor.example

import com.jelly.thor.commonutils.PreferencesBy

/**
 * 类描述：sharePreferences扩展<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/11/15 14:46 <br/>
 */
inline fun <reified I, T> I.preferencesExt(default: T, noinline key: () -> String = {""}) =
    PreferencesBy(AppContext, default, key, "shandian")