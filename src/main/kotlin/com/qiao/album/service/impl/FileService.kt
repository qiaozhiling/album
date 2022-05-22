package com.qiao.album.service.impl

import cn.hutool.crypto.SecureUtil
import com.qiao.album.dao.ImageDao
import com.qiao.album.pojo.domain.Image
import com.qiao.album.utils.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class FileService {

    @Autowired
    private lateinit var imageDao: ImageDao

    @Transactional(rollbackFor = [Exception::class])
    fun saveImg(file: MultipartFile): Int {
        val contentType = FileUtil.getContentType(file)
        val suffix = FileUtil.getSuffix(file.originalFilename!!).lowercase()
        val path = "\\images\\${SecureUtil.md5(file.inputStream)}.$suffix"
        var imageId = imageDao.getImageIdByPath(path)
        if (imageId == null) {
            val image = Image().apply {
                this.path = path
                size = file.size
                this.contentType = contentType
            }
            imageDao.addImage(image)
            imageId = image.id
            FileUtil.save(file, path)
        }
        return imageId!!
    }


}