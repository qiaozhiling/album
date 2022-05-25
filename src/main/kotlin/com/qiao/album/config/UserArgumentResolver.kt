package com.qiao.album.config

import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.utils.JwtUtil
import com.qiao.album.utils.RedisUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

@Component
class UserArgumentResolver : HandlerMethodArgumentResolver {

    @Autowired
    private lateinit var jwtProperties: JwtProperties

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType == LoginUser::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val token = request!!.getHeader(jwtProperties.header)
        val redisKey = jwtProperties.redisKey + JwtUtil.getClaimsByToken(token).subject
        return RedisUtils.get(redisKey) as LoginUser?

    }

}