package com.qiao.album.controller

import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.pojo.vo.User
import com.qiao.album.service.AuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@Api(description = "用户相关")
@RestController
@RequestMapping("/user")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    @ApiOperation(value = "获取token，并登入")
    @PostMapping("/token")
    fun token(
        @ApiParam(name = "用户名", required = true) @RequestParam(required = true) username: String,
        @ApiParam(name = "用户密码", required = true) @RequestParam(required = true) password: String
    ): ComResult<String?> {
        return authService.getToken(username, password)
    }

    @ApiOperation(value = "新建用户")
    @PostMapping("/new")
    fun register(
        @RequestParam(name = "username", required = true) name: String,
        @RequestParam(name = "password", required = true) password: String,
        @RequestParam(name = "email", required = true) email: String,
    ): ComResult<String?> {
        val user = User(name, password, email)
        return authService.register(user)
    }

    @ApiOperation(value = "注销")
    @GetMapping("/logout")
    fun logout(@ApiIgnore user: LoginUser): ComResult<String?> {
        return authService.logout(user.id)
    }

    @ApiOperation(value = "通过用户id获取用户")
    @PostMapping("/{userId}")
    fun getUser(
        @PathVariable("userId") userId: Int
    ): ComResult<User> {
        return authService.getUser(userId)
    }

}