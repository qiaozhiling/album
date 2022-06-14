package com.qiao.album.filter

import com.qiao.album.config.JwtProperties
import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.utils.*
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter
@Component
class JwtTokenAuthFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var serverProperties: ServerProperties

    @Autowired
    private lateinit var jwtProperties: JwtProperties

    @Value("\${server.white}")
    private lateinit var whiteApi: Array<String>

    private val contextPath by lazy { serverProperties.servlet.contextPath }

    private val whitePath by lazy {
        whiteApi.map {
            "$contextPath$it"
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.characterEncoding = "utf8"
        request.characterEncoding = "utf8"
        try {
            if (request.requestURI.mat(whitePath)) {
                filterChain.doFilter(request, response)
                return
            }
        } catch (e: Exception) {
            loge("e {}", e)
            response.sendError(501, "UNKNOWN ERROR")
            return
        }

        var token = request.getHeader(jwtProperties.header)
        if (token == null) {
            response.unauth()
            return
        }
        if (!token.startsWith(jwtProperties.startWith)) {
            response.unauth()
            return
        }
        token = token.replace(jwtProperties.startWith, "")
        val claims = try {
            JwtUtil.getClaimsByToken(token)
        } catch (e: ExpiredJwtException) {
            response.unauth("token过期")
            return
        } catch (e: Exception) {
            response.unauth("token解析错误")
            return
        }
        val redisKey = jwtProperties.redisKey + claims.subject
        val loginUser = RedisUtils.get(redisKey) as LoginUser?
        if (loginUser == null) {
            response.unauth()
            return
        }
        filterChain.doFilter(request, response)
    }


}