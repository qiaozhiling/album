package com.qiao.album.service

import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.Album
import com.qiao.album.pojo.vo.ImageVo
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

interface AlbumService {
    fun newAlbum(album: Album): ComResult<String?>
    fun getMyAlbum(pageSize: Int, index: Int, id: Int): ComResult<Pages<Album>>
    fun getOpenAlbum(pageSize: Int, index: Int): ComResult<Pages<Album>>
    fun deleteAlbum(album: Album): ComResult<String?>
    fun updateAlbum(album: Album): ComResult<String?>
    fun saveFiles(album: Album, images: Array<MultipartFile>): ComResult<Int>
    fun getImage(albumId: Int, imalId: Int, response: HttpServletResponse)
    fun getImages(albumId: Int, pageSize: Int, index: Int, id: Int, host: String): ComResult<Pages<ImageVo>>
    fun deleteImage(album: Album, imalIds: ArrayList<Int>): ComResult<String>
    fun queryAlbum(pageSize: Int, index: Int, content: String): ComResult<Pages<Album>>

}