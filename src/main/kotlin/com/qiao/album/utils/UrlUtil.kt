package com.qiao.album.utils

fun String.mat(s: List<String>): Boolean {
    s.forEach {
        if (it.endsWith("*")) {
            if (this.startsWith(it.replace("*", ""))) return true
        } else {
            if (this == it) return true
        }

    }
    return false
}