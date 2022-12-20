package com.jelly.thor.commonutils

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * 类描述：ImageView扩展方法<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/7/17 10:29 <br/>
 */
//@JvmOverloads
//fun ImageView.glideIntoAsBitmapPath(
//    path: Any?,
//    roundDp: Int = 0,
//    @DrawableRes loadingPic: Int = R.drawable.default_logo,
//    @DrawableRes errorPic: Int = R.drawable.default_logo,
//    @DrawableRes noPic: Int = R.drawable.default_logo
//) {
//    var requestOptions = RequestOptions()
//        .placeholder(loadingPic)
//        .error(errorPic)
//        .fallback(errorPic)
//    if (roundDp > 0) {
//        if (this.scaleType == ImageView.ScaleType.CENTER_CROP) {
//            requestOptions = requestOptions.transform(
//                MultiTransformation(
//                    CenterCrop(),
//                    RoundedCorners(roundDp.dp2px())
//                )
//            )
//        } else {
//            requestOptions = requestOptions.transform(RoundedCorners(roundDp.dp2px()))
//        }
//    }
//
//    Glide.with(BaseApplication.getAppContext())
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
//
//@JvmOverloads
//fun ImageView.glideIntoAsBitmapPathMoreRound(
//    path: Any?,
//    topLeftRoundDp: Int = 0,
//    topRightRoundDp: Int = 0,
//    bottomRightRoundDp: Int = 0,
//    bottomLeftRoundDp: Int = 0,
//    @DrawableRes loadingPic: Int = R.drawable.default_logo,
//    @DrawableRes errorPic: Int = R.drawable.default_logo,
//    @DrawableRes noPic: Int = R.drawable.default_logo
//) {
//    var requestOptions = RequestOptions()
//        .placeholder(loadingPic)
//        .error(errorPic)
//        .fallback(errorPic)
//
//    if (this.scaleType == ImageView.ScaleType.CENTER_CROP) {
//        requestOptions = requestOptions.transform(
//            MultiTransformation(
//                CenterCrop(),
//                GranularRoundedCorners(
//                    topLeftRoundDp.dp2px().toFloat(),
//                    topRightRoundDp.dp2px().toFloat(),
//                    bottomRightRoundDp.dp2px().toFloat(),
//                    bottomLeftRoundDp.dp2px().toFloat()
//                )
//            )
//        )
//    } else {
//        requestOptions = requestOptions.transform(
//            GranularRoundedCorners(
//                topLeftRoundDp.dp2px().toFloat(),
//                topRightRoundDp.dp2px().toFloat(),
//                bottomRightRoundDp.dp2px().toFloat(),
//                bottomLeftRoundDp.dp2px().toFloat()
//            )
//        )
//    }
//
//    Glide.with(BaseApplication.getAppContext())
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