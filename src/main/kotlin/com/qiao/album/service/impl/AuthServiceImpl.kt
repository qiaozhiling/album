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
        if (password.length !in 6..18) {
            return ComResult.er("密码长度异常")
        }
        if (username.length !in 2..30) {
            return ComResult.er("用户名长度异常")
        }
        val user: User = userDao.selectUserByName(username) ?: return ComResult.er("用户不存在")
        if (user.password != PasswordEncoder.encode(password)) {
            return ComResult.er("密码错误")
        }
        val loginUser = LoginUser(user)
        RedisUtils.set(jwtProperties.redisKey + user.id, loginUser, jwtProperties.expire)
        val token = JwtUtil.generateToken("" + user.id)
        logi("登入成功 {} {}", user.id, user.name)
        return ComResult.ok("登入成功", token)
    }

    override fun register(user: User): ComResult<String?> {
        val regex = Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$")
        if (!user.email.matches(regex)) {
            return ComResult.er("邮箱格式错误")
        }
        if (user.password.length !in 6..18) {
            return ComResult.er("密码长度异常")
        }
        if (user.name.length !in 2..30) {
            return ComResult.er("用户名长度异常")
        }
        user.password = PasswordEncoder.encode(user.password)
        return try {
            val re = userDao.addUser(user)
            if (re == 1) {
                logi("登入成功 {} {}", user.id, user.name)
                ComResult.ok("创建成功")
            } else {
                logi("登入成功 {} {}", user.id, user.name)
                ComResult.er("创建用户失败")
            }
        } catch (e: DuplicateKeyException) {
            ComResult.er("用户已存在")
        } catch (e: Exception) {
            loge("创建用户失败 {}",e)
            ComResult.er("创建用户失败")
        }
    }

    override fun logout(userId: Int): ComResult<String?> {
        val redisKey = jwtProperties.redisKey + userId
        return if (RedisUtils.del(redisKey)) {
            logi("注销 $userId")
            ComResult.ok("注销成功")
        } else {
            ComResult.er("注销失败")
        }
    }

    override fun getUser(userId: Int): ComResult<User> {
        val user = userDao.selectUserById(userId) ?: return ComResult.er("用户不存在")
        return ComResult.ok("查询成功", user)
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