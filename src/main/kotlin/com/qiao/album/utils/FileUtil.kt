package com.qiao.album.utils

import cn.hutool.core.io.FileTypeUtil
import cn.hutool.extra.spring.SpringUtil
import com.qiao.album.pojo.domain.Image
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.http.HttpServletResponse


object FileUtil {
    private val basePath: String = SpringUtil.getProperty("images.path")

    private val SUFFIX = arrayOf(
        "png", "jpg", "jpeg", "gif",
    )

    private val TYPE = mapOf(
        "jpeg" to "image/jpeg",
        "gif" to "image/gif",
        "jpg" to "image/jpeg",
        "png" to "image/png"
    )

    fun save(file: MultipartFile, path: String) {
        val realPath = "$basePath$path"
        val dest = File(realPath)
        if (!dest.parentFile.exists()) {
            dest.parentFile.mkdirs()
        }
        file.transferTo(dest)
    }

    fun getContentType(multipartFile: MultipartFile): String {

        val fileName = multipartFile.originalFilename
        if (fileName == "" || fileName == null) {
            throw RuntimeException("文件类型错误")
        }
        val suffix = getSuffix(fileName).lowercase()
        val type = FileTypeUtil.getType(multipartFile.inputStream).lowercase()
        if (!SUFFIX.contains(type) || !SUFFIX.contains(suffix)) {
            throw RuntimeException("文件类型错误")
        }
        return TYPE[suffix]!!
    }

    fun getSuffix(fileName: String) = fileName.substring(fileName.lastIndexOf(".") + 1)

    fun load(image: Image, response: HttpServletResponse) {
        response.contentType = image.contentType
        response.setContentLength(image.size.toInt())
        val outputString = response.outputStream
        val dest = File("$basePath${image.path}")
        val bytes = ByteArray(1024)
        val inputStream = dest.inputStream()
        var len = inputStream.read(bytes)
        while (len != -1) {
            outputString.write(bytes, 0, len)
            outputString.flush()
            len = inputStream.read(bytes)
        }
    }


}