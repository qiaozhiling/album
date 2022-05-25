package com.qiao.album.controller

import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.Album
import com.qiao.album.pojo.vo.ImageVo
import com.qiao.album.service.AlbumService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletRequest

@Api(description = "相册相关")
@RestController
@RequestMapping("/album")
class AlbumController {

    @Autowired
    private lateinit var albumService: AlbumService

    @ApiOperation("新建相册")
    @PostMapping("/new")
    fun newAlbum(
        @ApiParam(name = "相册名字", required = true)
        @RequestParam(name = "name") name: String,
        @ApiParam(name = "相册描述", required = true)
        @RequestParam(name = "describe", defaultValue = "") describe: String,
        @ApiParam(name = "相册是否私有", required = false)
        @RequestParam(name = "private", defaultValue = "true") private: Boolean,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String?> {
        val album = Album(name, loginUser.id, describe, private)
        return albumService.newAlbum(album)
    }

    @ApiOperation("获取用户相册")
    @PostMapping("/get")
    fun getUserAlbum(
        @RequestParam(name = "pageSize", required = true) pageSize: Int,
        @RequestParam(name = "index", required = true) index: Int,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<Pages<Album>> {
        return albumService.getAlbum(pageSize, index, loginUser.id)
    }


    @ApiOperation("删除用户相册")
    @PostMapping("/delete")
    fun deleteAlbum(
        @ApiParam(name = "相册id", required = true) @RequestParam(name = "albumId", required = true) albumId: Int,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String?> {
        val album = Album().apply {
            id = albumId
            owner = loginUser.id
            this.name = name
        }
        return albumService.deleteAlbum(album)
    }

    @ApiOperation("更新用户相册信息")
    @PostMapping("/update")
    fun updateAlbum(
        @RequestParam(name = "albumId", required = true) albumId: Int,
        @RequestParam(name = "private", required = false) private: Boolean?,
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "describe", required = false) describe: String?,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String?> {
        val album = Album().apply {
            this.owner = loginUser.id
            this.id = albumId
            this.name = name
            this.private = private
            this.describe = describe
        }
        return albumService.updateAlbum(album)
    }

    @ApiOperation("获取相册的图")
    @PostMapping("/images")
    fun getImages(
        @RequestParam(name = "albumId", required = true) albumId: Int,
        @RequestParam(name = "pageSize", required = true) pageSize: Int,
        @RequestParam(name = "index", required = true) index: Int,
        @ApiIgnore request: HttpServletRequest,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<Pages<ImageVo>> {
        val host = request.requestURL.replace(request.requestURI.toRegex(), "")
        return albumService.getImages(albumId, pageSize, index, loginUser.id,host)
    }
}