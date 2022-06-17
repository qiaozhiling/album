package com.qiao.album.controller

import com.qiao.album.pojo.domain.LoginUser
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.Album
import com.qiao.album.pojo.vo.SayVo
import com.qiao.album.service.AlbumService
import com.qiao.album.service.ImageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletResponse

@Api(description = "图片相关")
@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/image")
class ImageController {

    @Autowired
    private lateinit var albumService: AlbumService

    @Autowired
    private lateinit var imageService: ImageService

    @ApiOperation("上传图片到相册，失败返回失败的个数")
    @PostMapping("/upload", headers = ["content-type=multipart/form-data"])
    fun upload(
        @ApiParam(value = "相册id", required = true) @RequestParam(name = "albumId", required = true) albumId: Int,
        @ApiParam(
            value = "图片文件 MultipartFile,swagger不支持在一个参数中上传文件数组，如果上传就会传递空值，建议测试多文件上传时用postman进行测试。https://blog.csdn.net/DravenLeft/article/details/106337529",
            required = true
        )
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
    @GetMapping("/get/{albumId}/{imalId}")
    fun getImage(
        @PathVariable albumId: Int,
        @PathVariable imalId: Int,
        response: HttpServletResponse,
    ) {
        albumService.getImage(albumId, imalId, response)
    }

    @ApiOperation("删除图片 可以批量删除")
    @PostMapping("/delete")
    fun deleteImage(
        @ApiParam(name = "相册id", required = true) @RequestParam(required = true, name = "albumId") albumId: Int,
        @ApiParam(name = "图片记录id", required = true) @RequestParam(
            required = true,
            name = "iids"
        ) imalIds: ArrayList<Int>,
        @ApiIgnore response: HttpServletResponse,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String> {
        val album = Album().apply {
            owner = loginUser.id
            id = albumId
        }
        return albumService.deleteImage(album, imalIds)
    }

    @ApiOperation("对图片进行评论")
    @PostMapping("/say")
    fun say(
        @ApiParam(value = "图片记录id", required = true) @RequestParam(required = true, name = "iid") imalId: Int,
        @ApiParam(value = "评论内容", required = true) @RequestParam(required = true, name = "message") message: String,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String> {
        return imageService.say(imalId, message, loginUser.id)
    }

    @ApiOperation("获取图片的评论")
    @PostMapping("/getsay")
    fun getComments(
        @ApiParam(value = "图片id", required = true) @RequestParam(required = true, name = "iid") imalId: Int,
        @ApiParam(value = "一页的大小", required = true) @RequestParam(name = "pageSize", required = true) pageSize: Int,
        @ApiParam(value = "第几页(1，2，3，...)", required = true) @RequestParam(name = "index", required = true) index: Int,
    ): ComResult<Pages<SayVo>> {
        return imageService.getComments(imalId, pageSize, index)
    }

    @ApiOperation("删除评论")
    @PostMapping("/deletesay")
    fun deleteComment(
        @ApiParam(value = "评论id", required = true) @RequestParam(required = true, name = "sayId") commentId: Int,
        @ApiIgnore loginUser: LoginUser
    ): ComResult<String> {
        return imageService.deleteComment(commentId, loginUser.id)
    }
}