package com.qiao.album.dao

import com.qiao.album.pojo.vo.ImageVo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param


@Mapper
interface ImalDao {
    fun addImal(map: Map<String, Any>): Int

    fun getImageIdById(imalId: Int): Int?

    fun getImageCount(albumId: Int): Int

    fun getImageVoByAlbumId(map: Map<String, Any>): ArrayList<ImageVo>

    fun deleteByIds(@Param("albumId") albumId: Int, @Param("ids") imalIds: List<Int>): Int
}