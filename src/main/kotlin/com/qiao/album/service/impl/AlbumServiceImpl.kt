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
        logd("新建相册 {}", album)
        return try {
            val has = albumDao.hasSameAlbum(album)
            if (has != 0) {
                logd("新建相册：失败 相册已经存在")
                ComResult.er("Album exists")
            } else {
                albumDao.newAlbum(album)
                logd("新建相册成功")
                ComResult.ok("Album created success")
            }
        } catch (e: Exception) {
            loge("新建相册失败 {}", e)
            ComResult.er("Album created fail")
        }
    }

    override fun getMyAlbum(pageSize: Int, index: Int, id: Int): ComResult<Pages<Album>> {
        logd("获取用户相册 id:{}", id)
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
            logd("获取用户相册：成功 ")
            return ComResult.success(pages)
        } catch (e: Exception) {
            loge("获取用户相相册失败 {}", e)
            return ComResult.fail()
        }
    }

    override fun getOpenAlbum(pageSize: Int, index: Int): ComResult<Pages<Album>> {
        logd("获取开放相册")
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
            logd("获取开放相册：成功")
            ComResult.success(pages)
        } catch (e: Exception) {
            loge("获取开放相册：失败 {}", e)
            ComResult.fail()
        }
    }

    override fun deleteAlbum(album: Album): ComResult<String?> {
        logd("删除相册")
        return try {
            val res = albumDao.deleteAlbum(album)
            if (res > 0) {
                logd("删除相册成功")
                ComResult.success()
            } else {
                logd("删除相册：失败 相册不存在")
                ComResult.er("Album does not exist")
            }
        } catch (e: Exception) {
            loge("删除相册失败 {}", e)
            ComResult.fail()
        }
    }

    override fun updateAlbum(album: Album): ComResult<String?> {
        logd("更新相册信息")
        return try {
            val has = albumDao.updateAlbum(album)
            if (has == 1) {
                logd("更新相册信息：成功")
                ComResult.success()
            } else {
                logd("更新相册信息：fail 相册不存在")
                ComResult.er("Album does not exist")
            }
        } catch (e: Exception) {
            loge("更新相册失败 {}", e)
            ComResult.fail()
        }
    }

    override fun saveFiles(album: Album, images: Array<MultipartFile>): ComResult<Int> {
        logd("上传图片 相册{} 图片数量{}", album, images.size)
        try {
            if (albumDao.hasRight(album) == 0) {
                logd("上传图片：fail 没有上传权限")
                return ComResult.er("no permission")
            }
            if (albumDao.hasRight(album) > 1) {
                loge("没有上传权限 相册数量>1 {}", album)
                return ComResult.er("Number of same album >1")
            }
        } catch (e: Exception) {
            loge("上传图片：fail {}", e)
            return ComResult.fail()
        }
        var counter = 0 // 出错个数
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
                counter++
            }
        }
        return if (counter == 0) {
            logd("上传图片：成功")
            ComResult.success()
        } else {
            logd("上传图片：失败 counts{}", counter)
            ComResult.er("upload fail", counter)
        }
    }

    override fun getImage(albumId: Int, imalId: Int, userId: Int, response: HttpServletResponse) {
        logd("获取单张图片 albumId{} imalId{} userId{}", albumId, imalId, userId)
        try {
            val album = albumDao.getAlbumById(albumId)
            if (album == null) {
                logd("获取单张图片：fail 相册不存在")
                response.no()
                return
            }
            if (album.owner != userId && album.private) {
                logd("获取单张图片：fail 没权限")
                response.forbid()
                return
            }
            val imageId = imalDao.getImageIdById(imalId)
            if (imageId == null) {
                logd("获取单张图片：fail 图片id不存在")
                response.no()
                return
            }
            val image = imageDao.getImageById(imageId)
            if (image == null) {
                logd("获取单张图片：fail 图片id不存在")
                response.no()
                return
            }
            FileUtil.load(image, response)
            logd("获取单张图片：success")
        } catch (e: Exception) {
            loge("获取单张图片：失败 e:{} albumId:{} imalId:{}", e, albumId, imalId)
            response.no()
        }
    }

    override fun getImages(albumId: Int, pageSize: Int, index: Int, id: Int, host: String): ComResult<Pages<ImageVo>> {
        logd("获取相册内的图片 albumId{} pageSize{} index{} id{}", albumId, pageSize, index, id)
        return try {
            val album = albumDao.getAlbumById(albumId) ?: return ComResult.er("相册不存在")
            if (album.owner != id && album.private) {
                return ComResult.er("no permission")
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
            ComResult.success(pages)
        } catch (e: Exception) {
            loge("获取相册图片失败 {} albumid:{} userId:{} ", e, albumId, id)
            ComResult.fail()
        }
    }

    override fun deleteImage(album: Album, imalIds: ArrayList<Int>): ComResult<String> {
        try {
            if (albumDao.hasRight(album) == 0) {
                return ComResult.er("no permission")
            }
            if (albumDao.hasRight(album) > 1) {
                loge("相册数量>1 {}", album)
                return ComResult.er("Number of same album >1")
            }
            imalDao.deleteByIds(album.id, imalIds)
            return ComResult.ok("success")
        } catch (e: Exception) {
            loge("删除图片失败 {} imalIds:{}", e, imalIds)
            return ComResult.fail()
        }

    }
}