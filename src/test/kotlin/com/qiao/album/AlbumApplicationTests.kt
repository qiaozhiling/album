package com.qiao.album

import com.qiao.album.config.JwtProperties
import com.qiao.album.dao.UserDao
import com.qiao.album.utils.FileUtil
import com.qiao.album.utils.PasswordEncoder
import com.qiao.album.utils.logi
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlbumApplicationTests {

    @Autowired
    lateinit var jwtProperties: JwtProperties

//    @Autowired
//    lateinit var userMapper: UserDao

    @Test
    fun contextLoads() {
//        val user = LoginUser(1, "", "", 343, "fsdf@dsf.com", null, "")
//        println(user)
        println(jwtProperties)
    }

    @Test
    fun test2() {
        val psd = PasswordEncoder.encode("123456")
        val psd2 = PasswordEncoder.encode("123456")
        println(psd)
        println(psd2)
    }

    @Test
    fun test3() {
        logi("啊哈哈")
        val suffix = FileUtil.getSuffix("fasdf.jpg")
        println(suffix)
    }

    @Value("\${server.white}")
    private lateinit var whiteApi: Array<String>

    @Test
    fun test4() {
        println(whiteApi)
    }



}
