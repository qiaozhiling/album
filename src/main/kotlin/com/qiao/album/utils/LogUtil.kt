package com.qiao.album.utils

import org.slf4j.LoggerFactory

fun Any.logi(var1: String, vararg var2: Any) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.info(var1, var2)
}

fun Any.logi(var1: String) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.info(var1)
}

fun Any.logd(var1: String, vararg var2: Any) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.debug(var1, var2)
}

fun Any.logd(var1: String) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.debug(var1)
}

fun Any.loge(var1: String, vararg var2: Any) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.error(var1, var2)
}

fun Any.loge(var1: String) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    logger.error(var1)
}




