package com.qiao.album.service.impl

import com.qiao.album.dao.AlbumDao
import com.qiao.album.dao.ImageDao
import com.qiao.album.dao.ImalDao
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.Album
import com.qiao.album.pojo.vo.ImageVo
import com.qiao.album.service.AlbumService
import com.qiao.album.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse


@Service
class AlbumServiceImpl : AlbumService {

    @Value("\${server.servlet.context-path}")
    private lateinit var contentPath: String

    @Autowired
    private lateinit var imageDao: ImageDao

    @Autowired
    private lateinit var imalDao: ImalDao

    @Autowired
    private lateinit var albumDao: AlbumDao

    @Autowired
    private lateinit var fileService: FileService

    override fun newAlbum(album: Album): ComResult<String?> {
        return try {
            val has = albumDao.hasSameAlbum(album)
            if (has != 0) {
                ComResult.er("相册已经存在")
            } else {
                albumDao.newAlbum(album)
                ComResult.ok("新建相册成功")
            }
        } catch (e: Exception) {
            loge("新建相册失败 {}", e)
            ComResult.er("新建失败")
        }
    }

    override fun getAlbum(pageSize: Int, index: Int, id: Int): ComResult<Pages<Album>> {
        try {
            val total = albumDao.getAlbumCount(id)
            val offset = pageSize * (index - 1)
            val map = mapOf(
                "id" to id, "pageSize" to pageSize, "offset" to offset
            )
            val albumList = albumDao.getAlbumByUserId(map)
            val pages = Pages<Album>().apply {
                records = albumList
                this.total = total
                this.index = index
                this.size = pageSize
            }
            logd("获取")
            return ComResult.ok("获取成功", pages)
        } catch (e: Exception) {
            loge("获取相册失败 {}", e)
            return ComResult.er("获取失败")
        }

    }

    override fun deleteAlbum(album: Album): ComResult<String?> {
        return try {
            val res = albumDao.deleteAlbum(album)
            if (res > 0) {
                ComResult.ok("删除成功")
            } else {
                ComResult.er("相册不存在")
            }
        } catch (e: Exception) {
            loge("删除相册失败 {}", e)
            ComResult.er("删除失败")
        }
    }

    override fun updateAlbum(album: Album): ComResult<String?> {
        return try {
            val has = albumDao.updateAlbum(album)
            if (has == 1) {
                ComResult.ok("更新相册信息成功")
            } else {
                ComResult.er("相册不存在")
            }
        } catch (e: Exception) {
            loge("更新相册失败 {}", e)
            ComResult.er("跟新相册信息失败")
        }
    }

    override fun saveFiles(album: Album, images: Array<MultipartFile>): ComResult<Int> {
        if (albumDao.hasRight(album) == 0) {
            return ComResult.er("没有上传权限哦")
        }
        if (albumDao.hasRight(album) > 1) {
            logd("相册数量>1 {}", album)
            return ComResult.er("同相册数量>1")
        }
        var a = 0
        images.forEach { file ->
            try {
                fileService.run {
                    val imageId = saveImg(file)
                    val map = mapOf(
                        "albumId" to album.id, "imageId" to imageId, "imageName" to file.originalFilename!!
                    )
                    imalDao.addImal(map)
                }
            } catch (e: Exception) {
                loge("保存图片失败 {}", e)
                a++
            }
        }
        return if (a == 0) {
            ComResult.ok("上传成功")
        } else {
            ComResult.er("上传失败", a)
        }
    }

    override fun getImage(albumId: Int, imalId: Int, userId: Int, response: HttpServletResponse) {
        try {
            val album = albumDao.getAlbumById(albumId)
            if (album == null) {
                response.no()
                return
            }
            if (album.owner != userId && album.private) {
                response.forbid()
                return
            }
            val imageId = imalDao.getImageIdById(imalId)
            if (imageId == null) {
                response.no()
                return
            }
            val image = imageDao.getImageById(imageId)
            if (image == null) {
                response.no()
                return
            }
            FileUtil.load(image, response)
        } catch (e: Exception) {
            loge("获取图片失败 e:{} albumId:{} imalId:{}", e, albumId, imalId)
            response.no()
        }
    }

    override fun getImages(albumId: Int, pageSize: Int, index: Int, id: Int, host: String): ComResult<Pages<ImageVo>> {
        return try {
            val album = albumDao.getAlbumById(albumId) ?: return ComResult.er("相册不存在")
            if (album.owner != id && album.private) {
                return ComResult.er("宁无权查看")
            }
            val total = imalDao.getImageCount(albumId)
            val offset = pageSize * (index - 1)
            val map = mapOf(
                "albumId" to albumId, "pageSize" to pageSize, "offset" to offset
            )
            val imageVos = imalDao.getImageVoByAlbumId(map).map {
                it.url = host + contentPath + "/image/" + albumId + "/" + it.iid
                it
            }

            val pages = Pages<ImageVo>().apply {
                this.total = total
                this.index = index
                this.size = pageSize
                records = imageVos
            }
            ComResult.ok("获取成功", pages)
        } catch (e: Exception) {
            loge("获取相册图片失败 {} albumid:{} userId:{} ", e, albumId, id)
            ComResult.er("获取失败")
        }
    }

    override fun deleteImage(album: Album, imalIds: ArrayList<Int>): ComResult<String> {
        try {
            if (albumDao.hasRight(album) == 0) {
                return ComResult.er("没有上传权限哦")
            }
            if (albumDao.hasRight(album) > 1) {
                loge("相册数量>1 {}", album)
                return ComResult.er("同相册数量>1")
            }
            imalDao.deleteByIds(album.id, imalIds)
            return ComResult.ok("删除成功")
        } catch (e: Exception) {
            loge("删除图片失败 {} imalIds:{}", e, imalIds)
            return ComResult.er("删除失败")
        }

    }

    override fun getOpenAlbum(pageSize: Int, index: Int): ComResult<Pages<Album>> {
        return try {
            val total = albumDao.getOpenAlbumCount()
            val offset = pageSize * (index - 1)
            val map = mapOf(
                "pageSize" to pageSize,
                "offset" to offset
            )
            val albumList = albumDao.getOpenAlbum(map)
            val pages = Pages<Album>().apply {
                records = albumList
                this.total = total
                this.index = index
                this.size = pageSize
            }
            logd("获取open相册")
            ComResult.ok("获取成功", pages)
        } catch (e: Exception) {
            logd("获取open相册失败 {}", e)
            ComResult.er("获取失败")
        }
    }
}