package com.qiao.album.utils

import java.math.BigInteger
import java.security.MessageDigest

object PasswordEncoder {
    fun encode(password: String): String {
        val salt = "&*Gh340).+"
        val md = MessageDigest.getInstance("SHA").apply {
            update((salt + password).toByteArray())
        }
        return BigInteger(md.digest()).toString(32)
    }
}