package com.jelly.thor.commonutils.annotation;

import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * 类描述：Visibility注解类 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/7/31 10:22 <br/>
 */
@IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
@Retention(RetentionPolicy.SOURCE)
public @interface Visibility {}
