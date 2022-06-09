package com.qiao.album.controller

import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.vo.Album
import com.qiao.album.service.AlbumService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/image")
class ImageController {

    @Autowired
    private lateinit var albumService: AlbumService


    @ApiOperation("上传图片到相册")
    @PostMapping("/upload")
    fun upload(
        @ApiParam(name = "相册id", required = true)
        @RequestParam(name = "albumId", required = true) albumId: Int,
        @ApiParam(name = "图片文件 MultipartFile", required = true)
        @RequestParam(name = "images", required = true) images: Array<MultipartFile>,
        @ApiIgnore httpServletRequest: MultipartHttpServletRequest,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<Int> {
        val album = Album().apply {
            id = albumId
            owner = loginUser.id
        }
        return albumService.saveFiles(album, images)
    }


    @ApiIgnore
    @GetMapping("/{albumId}/{imalId}")
    fun getImage(
        @PathVariable albumId: Int,
        @PathVariable imalId: Int,
        response: HttpServletResponse,
        loginUser: LoginUser
    ) {
        albumService.getImage(albumId, imalId, loginUser.id, response)
    }

    @PostMapping("/delete")
    fun deleteImage(
        @ApiParam(name = "相册id", required = true)
        @RequestParam(required = true, name = "albumId") albumId: Int,
        @ApiParam(name = "记录id", required = true)
        @RequestParam(required = true, name = "iids") imalIds: ArrayList<Int>,
        @ApiIgnore response: HttpServletResponse,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String> {
        val album = Album().apply {
            owner = loginUser.id
            id = albumId
        }
        return albumService.deleteImage(album, imalIds)
    }
}