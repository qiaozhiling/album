package com.qiao.album.dao

import com.qiao.album.pojo.domain.Image
import com.qiao.album.pojo.vo.SayVo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface ImageDao {
    fun addImage(image: Image): Int // 返回的主键

    fun getImageIdByPath(path: String): Int?

    fun getImageById(imageId: Int): Image?

    fun say(
        @Param("imalId") imageId: Int,
        @Param("message") message: String,
        @Param("userId") userId: Int
    ): Int

    fun getCommentCount(imalId: Int): Int

    fun getComments(
        @Param("imalId") imalId: Int,
        @Param("pageSize") pageSize: Int,
        @Param("offset") offset: Int,
    ): ArrayList<SayVo>

    fun getCommentById(commentId: Int): SayVo?

    fun deleteCommentById(commentId: Int): Int
}

