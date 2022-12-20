package com.jelly.thor.commonutils

import android.content.*
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * 类描述：文件扩展 <br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/12/20 10:33 <br/>
 */
/**
 * 图片文件夹
 */
private val PIC_DIR = Environment.DIRECTORY_PICTURES

/**
 * 回传文件使用 ，方便直接通过model获取到文件file
 */
private class CallBackFile(var file: File? = null)

/**
 * 复制图片到相册
 * @param fileName 文件名。 需要携带后缀
 * @param relativePath 相对于Pictures的路径
 */
@JvmOverloads
fun File.save2Pic(
    context: Context,
    fileName: String,
    relativePath: String? = "/cmu/"
): Uri? {
    if (!this.exists() || !this.canRead()) {
        //Timber.tag("123===").w("复制图片到相册：文件不存在或不可读")
        return null
    }
    return this.inputStream().use {
        it.save2Pic(context, fileName, relativePath)
    }
}

/**
 * 保存图片图片到相册
 * @param fileName 文件名。 需要携带后缀
 * @param relativePath 相对于Pictures的路径
 */
@JvmOverloads
fun InputStream.save2Pic(
    context: Context,
    fileName: String,
    relativePath: String? = "/cmu/"
): Uri? {
    val contentResolver = context.contentResolver
    val callBackFile = CallBackFile()
    val imageUri = contentResolver.insertMediaImage(fileName, relativePath, callBackFile)
    if (imageUri == null) {
        //Timber.tag("123===").w("插入到相册中失败，获取uri为null")
        return null
    }

    (imageUri.outputStream(contentResolver) ?: return null).use { output ->
        this.use { input ->
            input.copyTo(output)
            imageUri.finishPending(context, contentResolver, callBackFile.file)
        }
    }
    return imageUri
}

/**
 * 保存Bitmap到相册的Pictures文件夹
 *
 * https://developer.android.google.cn/training/data-storage/shared/media
 *
 * @param context 上下文
 * @param fileName 文件名。 需要携带后缀
 * @param relativePath 相对于Pictures的路径
 * @param quality 质量
 */
@JvmOverloads
fun Bitmap.save2Pic(
    context: Context,
    fileName: String,
    relativePath: String? = "/cmu/",
    quality: Int = 100
): Uri? {
    // 插入图片信息
    val contentResolver = context.contentResolver
    val callBackFile = CallBackFile()
    val imageUri = contentResolver.insertMediaImage(fileName, relativePath, callBackFile)
    if (imageUri == null) {
        //Timber.tag("123===").w("插入到相册中失败，获取uri为null")
        return null
    }

    // 保存图片
    (imageUri.outputStream(contentResolver) ?: return null).use {
        val format = fileName.getBitmapFormat()
        this@save2Pic.compress(format, quality, it)
        imageUri.finishPending(context, contentResolver, callBackFile.file)
    }
    return imageUri
}

/**
 * 通知刷新
 */
private fun Uri.finishPending(
    context: Context,
    contentResolver: ContentResolver,
    callBackFile: File?
) {
    val contentValues = ContentValues()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // Android Q添加了IS_PENDING状态，为0时其他应用才可见
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        contentResolver.update(this, contentValues, null, null)
    } else {
        if (callBackFile != null) {
            contentValues.put(MediaStore.Images.Media.SIZE, callBackFile.length())
        }
        contentResolver.update(this, contentValues, null, null)
        // 通知媒体库更新
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, this)
        context.sendBroadcast(intent)
    }
}

private fun Uri.outputStream(contentResolver: ContentResolver): OutputStream? {
    return try {
        contentResolver.openOutputStream(this)
    } catch (e: Exception) {
        //Timber.tag("123===").w(e, "uri 转outputStream 异常")
        null
    }
}

/**
 * 获取bitmap 压缩格式
 */
private fun String.getBitmapFormat(): Bitmap.CompressFormat {
    val fileName = this.lowercase()
    return when {
        fileName.endsWith(".png") -> Bitmap.CompressFormat.PNG
        fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") -> Bitmap.CompressFormat.JPEG
        fileName.endsWith(".webp") ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) Bitmap.CompressFormat.WEBP_LOSSLESS
            else Bitmap.CompressFormat.WEBP
        else -> Bitmap.CompressFormat.PNG
    }
}

/**
 * @link 系统文件 MediaFile中可以查看/copy
 */
private fun String.getMineType(): String {
    val fileName = this.lowercase()
    return when {
        fileName.endsWith(".png") -> "image/png"
        fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") -> "image/jpeg"
        fileName.endsWith(".webp") -> "image/webp"
        fileName.endsWith(".gif") -> "image/gif"
        fileName.endsWith(".pdf") -> "application/pdf"
        fileName.endsWith(".xlsx") -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        fileName.endsWith(".docx") -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        else -> "file/*"
    }
}

/**
 * 插入图片到媒体库中
 */
private fun ContentResolver.insertMediaImage(
    fileName: String,
    relativePath: String?,
    callBackFile: CallBackFile? = null
): Uri? {
    //图片信息
    val contentResolver = ContentValues().apply {
        val mineType = fileName.getMineType()
        if (mineType.startsWith("image")) {
            put(MediaStore.Images.Media.MIME_TYPE, mineType)
        }
        val time = System.currentTimeMillis() / 1000
        put(MediaStore.Images.Media.DATE_ADDED, time)
        put(MediaStore.Images.Media.DATE_MODIFIED, time)
    }
    //保存位置
    val collection: Uri
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val path = if (relativePath.isNullOrEmpty()) {
            PIC_DIR
        } else {
            "${PIC_DIR}/${relativePath}"
        }
        contentResolver.apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.RELATIVE_PATH, path)
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        // 高版本不用查重直接插入，会自动重命名
    } else {
        val picturesDir = Environment.getExternalStoragePublicDirectory(PIC_DIR)
        val saveDir =
            if (relativePath.isNullOrEmpty()) picturesDir else File(picturesDir, relativePath)
        if (!saveDir.exists() && !saveDir.mkdirs()) {
            //Timber.tag("123===").w("不能创建pictures 文件夹")
            return null
        }
        //查重文件，如果重复文件名后添加数字
        var imageFile = File(saveDir, fileName)
        val fileNameWithoutExtension = imageFile.nameWithoutExtension
        val fileExtension = imageFile.extension

        var queryUri = this.queryMediaImage28(imageFile.absolutePath)
        var suffix = 1
        while (queryUri != null) {
            val newFileName = fileNameWithoutExtension + "(${suffix++}).${fileExtension}"
            imageFile = File(saveDir, newFileName)
            queryUri = this.queryMediaImage28(imageFile.absolutePath)
        }
        contentResolver.apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imageFile.name)
            put(MediaStore.Images.Media.DATA, imageFile.absolutePath)
        }

        callBackFile?.file = imageFile// 回传文件，用于设置文件大小
        collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    //插入图片信息
    return this.insert(collection, contentResolver)
}

/**
 * Android Q以下版本，查询媒体库中当前路径是否存在
 * @return 返回null表示不存在
 */
private fun ContentResolver.queryMediaImage28(path: String): Uri? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return null

    val imageFile = File(path)
    if (imageFile.canRead() && imageFile.exists()) {
        //Timber.tag("123===").w("查询到文件：${path}存在")
        return Uri.fromFile(imageFile)
    }
    //保存的位置
    val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    //查询是否已经存在相同的文件
    val query = this.query(
        collection,
        arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA),
        "${MediaStore.Images.Media.DATA} == ?",
        arrayOf(path),
        null
    )
    query?.use {
        while (it.moveToNext()) {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val id = it.getLong(idColumn)
            val existsUri = ContentUris.withAppendedId(collection, id)
            //Timber.tag("123===").w("查询到文件路径=${path} 存在相同的uri=${existsUri}")
            return existsUri
        }
    }
    return null
}
