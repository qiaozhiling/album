package com.qiao.album.dao

import com.qiao.album.pojo.domain.Image
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ImageDao {
    fun addImage(image: Image): Int // 返回的主键

    fun getImageIdByPath(path: String): Int?

    fun getImageById(imageId: Int): Image?
}

