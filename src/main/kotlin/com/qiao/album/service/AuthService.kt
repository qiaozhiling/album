package com.qiao.album.service

import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.vo.User

interface AuthService {
    fun getToken(username: String, password: String): ComResult<String?>
    fun register(user: User): ComResult<String?>
    fun logout(userId: Int): ComResult<String?>
    fun getUser(userId: Int): ComResult<User>
//     fun password(oldPassword: String, newPassword: String, id: Int): ComResult<String>

}