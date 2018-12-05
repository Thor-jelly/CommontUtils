package com.jelly.thor.example

import android.app.Application
import android.content.ContextWrapper

/**
 * 类描述：//TODO:(这里用一句话描述这个方法的作用)    <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/11/15 14:45 <br/>
 */

private lateinit var INSTANCE: Application

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

object AppContext: ContextWrapper(INSTANCE)