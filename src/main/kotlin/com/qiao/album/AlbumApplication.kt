package com.qiao.album

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@EnableConfigurationProperties
@EnableTransactionManagement
@SpringBootApplication
class AlbumApplication
fun main(args: Array<String>) {
    runApplication<AlbumApplication>(*args)
}
