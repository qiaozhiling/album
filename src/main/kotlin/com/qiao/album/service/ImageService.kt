package com.qiao.album.service

import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.SayVo

interface ImageService {
    fun say(imalId: Int, message: String, id: Int): ComResult<String>
    fun getComments(imalId: Int, pageSize: Int, index: Int): ComResult<Pages<SayVo>>
    fun deleteComment(commentId: Int, id: Int): ComResult<String>
}