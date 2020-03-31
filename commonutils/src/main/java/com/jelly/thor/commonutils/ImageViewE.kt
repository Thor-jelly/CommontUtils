package com.jelly.thor.commonutils

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * 类描述：ImageView扩展方法<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/7/17 10:29 <br/>
 */
//@JvmOverloads
//fun ImageView.glideIntoAsBitmapPath(path: String?,
//                                    @DrawableRes loadingPic: Int = R.drawable.ic_circular_picture_loading,
//                                    @DrawableRes errorPic: Int = R.drawable.ic_circular_picture_error,
//                                    @DrawableRes noPic: Int = R.drawable.ic_circular_picture_no
//) {
//    val requestOptions = RequestOptions()
//            .placeholder(loadingPic)
//            .error(errorPic)
//            .fallback(errorPic)
//
//    Glide.with(BaseApplication.getInstance().applicationContext)
//            .asBitmap()
//            .apply(requestOptions)
//            .load(
//                    if (path.isNullOrEmpty()) {
//                        noPic
//                    } else {
//                        path
//                    }
//            ).into(this)
//}