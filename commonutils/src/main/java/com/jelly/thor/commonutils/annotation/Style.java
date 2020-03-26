package com.jelly.thor.commonutils.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/10/15 15:26 <br/>
 */
@IntDef(value = {Style.NO_SET, Style.NORMAL, Style.BOLD, Style.ITALIC, Style.BOLD_ITALIC})
@Retention(RetentionPolicy.SOURCE)
public @interface Style {
    int NO_SET = -1;
    int NORMAL = 0;
    int BOLD = 1;
    int ITALIC = 2;
    int BOLD_ITALIC = 3;
}
