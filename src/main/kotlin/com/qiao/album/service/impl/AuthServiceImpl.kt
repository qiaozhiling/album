package com.qiao.album.service.impl

import com.qiao.album.config.JwtProperties
import com.qiao.album.dao.UserDao
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.pojo.vo.User
import com.qiao.album.service.AuthService
import com.qiao.album.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl : AuthService {
    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var jwtProperties: JwtProperties

    override fun getToken(username: String, password: String): ComResult<String?> {
        try {
            if (password.length !in 6..18) {
                return ComResult.er("Abnormal password length")
            }
            if (username.length !in 2..30) {
                return ComResult.er("Abnormal user name length")
            }
            val user: User = userDao.selectUserByName(username) ?: return ComResult.er("user does not exist")
            if (user.password != PasswordEncoder.encode(password)) {
                return ComResult.er("Password error")
            }
            val loginUser = LoginUser(user)
            RedisUtils.set(jwtProperties.redisKey + user.id, loginUser, jwtProperties.expire)
            val token = JwtUtil.generateToken("" + user.id)
            logd("登入成功 {}", user)
            return ComResult.ok("Login success", token)
        } catch (e: Exception) {
            loge("登入失败 {}", e)
            return ComResult.er("Login fail")
        }

    }

    override fun register(user: User): ComResult<String?> {
        val regex = Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$")
        if (!user.email.matches(regex)) {
            return ComResult.er("Mailbox format error")
        }
        if (user.password.length !in 6..18) {
            return ComResult.er("Abnormal password length")
        }
        if (user.name.length !in 2..30) {
            return ComResult.er("Abnormal user name length")
        }
        user.password = PasswordEncoder.encode(user.password)
        return try {
            val re = userDao.addUser(user)
            if (re == 1) {
                logd("注册成功 {}", user)
                ComResult.ok("User created success")
            } else {
                logd("注册失败 {}", user)
                ComResult.er("User created fail")
            }
        } catch (e: DuplicateKeyException) {
            logd("注册失败 用户存在 {}", user)
            ComResult.er("User exists")
        } catch (e: Exception) {
            loge("创建用户失败 {}", e)
            ComResult.er("User created fail")
        }
    }

    override fun logout(userId: Int): ComResult<String?> {
        val redisKey = jwtProperties.redisKey + userId
        return if (RedisUtils.del(redisKey)) {
            logd("注销 $userId")
            ComResult.ok("Logout success")
        } else {
            loge("注销失败 userId:{}", userId)
            ComResult.er("Logout fail")
        }
    }

    override fun getUser(userId: Int): ComResult<User> {
        val user = userDao.selectUserById(userId) ?: return ComResult.er("User does not exist")
        return ComResult.ok("Query success", user)
    }

    //    override fun password(oldPassword: String, newPassword: String, userId: Int): ComResult<String> {
//        return try {
//            val user = userDao.selectUserById(userId) ?: return ComResult.er("用户不存在")
//            if (user.password != PasswordEncoder.encode(oldPassword)){
//                return ComResult.er("原密码错误")
//            }
//
//            userDao.updatePassword()
//            logout(userId)
//            ComResult.ok("修改密码成功")
//        } catch (e: Exception) {
//            ComResult.er("修改失败")
//        }
//    }
}