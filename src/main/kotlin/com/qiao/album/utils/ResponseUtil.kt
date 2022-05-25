package com.qiao.album.utils

import com.qiao.album.pojo.dto.ComResult
import javax.servlet.http.HttpServletResponse


fun HttpServletResponse.unauth() {
    unauth("用户未登入")
}

fun HttpServletResponse.unauth(msg: String) {
    contentType = "application/json;charset=utf8"
//    characterEncoding = "gbk"
    logi(msg)
    status = HttpServletResponse.SC_UNAUTHORIZED
    val result = ComResult.er<String>(msg)
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}

fun <T> HttpServletResponse.ok(msg: String, data: T) {
    contentType = "application/json;charset=utf-8"
    status = HttpServletResponse.SC_OK
    val result = ComResult.ok(msg, data)
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}

fun HttpServletResponse.no() {
    no("图片不存在")
}

fun HttpServletResponse.forbid() {
    contentType = "application/json;charset=utf8"
//    characterEncoding = "gbk"
    status = HttpServletResponse.SC_FORBIDDEN
    val result = ComResult.er<Any>("宁无权查看")
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}


fun HttpServletResponse.no(msg: String) {
    contentType = "application/json;charset=utf8"
//    characterEncoding = "gbk"
    status = HttpServletResponse.SC_NOT_FOUND
    val result = ComResult.er<Any>(msg)
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}