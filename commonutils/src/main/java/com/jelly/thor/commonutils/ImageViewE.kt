package com.jelly.thor.commonutils

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * 类描述：ImageView扩展方法<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/7/17 10:29 <br/>
 */
//@JvmOverloads
//fun ImageView.glideIntoAsBitmapPath(path: Any?,
//                                    roundDp: Int = 0,
//                                    @DrawableRes loadingPic: Int = R.drawable.ic_circular_picture_loading,
//                                    @DrawableRes errorPic: Int = R.drawable.ic_circular_picture_error,
//                                    @DrawableRes noPic: Int = R.drawable.ic_circular_picture_no
//) {
//    var requestOptions = RequestOptions()
//        .placeholder(loadingPic)
//        .error(errorPic)
//        .fallback(errorPic)
//    if (roundDp > 0) {
//        requestOptions = requestOptions.transform(RoundedCorners(roundDp.dp2px()))
//    }
//    Glide.with(QiyaApp.getInstance().applicationContext)
//        .asBitmap()
//        .apply(requestOptions)
//        .load(
//            if (path == null) {
//                noPic
//            } else if (path is String
//                && path.isEmpty()
//            ) {
//                noPic
//            } else {
//                path
//            }
//        ).into(this)
//}