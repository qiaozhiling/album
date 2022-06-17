package com.qiao.album.utils

import com.qiao.album.pojo.dto.ComResult
import javax.servlet.http.HttpServletResponse


fun HttpServletResponse.unauth() {
    unauth("Not logged in")
}

fun HttpServletResponse.unauth(msg: String) {
    contentType = "application/json;charset=utf8"
    characterEncoding = "utf8"
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
    characterEncoding = "utf8"
    status = HttpServletResponse.SC_OK
    val result = ComResult.ok(msg, data)
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}

fun HttpServletResponse.no() {
    no("image not found")
}

fun HttpServletResponse.forbid() {
    contentType = "application/json;charset=utf8"
    characterEncoding = "utf8"
    status = HttpServletResponse.SC_FORBIDDEN
    val result = ComResult.er<Any>("no permission")
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}

fun HttpServletResponse.unknown() {
    no("unknown error")
}

fun HttpServletResponse.no(msg: String) {
    contentType = "application/json;charset=utf8"
    characterEncoding = "utf8"
    status = HttpServletResponse.SC_NOT_FOUND
    val result = ComResult.er<Any>(msg)
    outputStream.run {
        write(result.toByteArray())
        flush()
    }
}