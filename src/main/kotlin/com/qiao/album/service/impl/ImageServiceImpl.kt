package com.qiao.album.service.impl

import com.qiao.album.dao.ImageDao
import com.qiao.album.pojo.dto.ComResult
import com.qiao.album.pojo.dto.Pages
import com.qiao.album.pojo.vo.SayVo
import com.qiao.album.service.ImageService
import com.qiao.album.utils.logd
import com.qiao.album.utils.loge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl : ImageService {

    @Autowired
    private lateinit var imageDao: ImageDao

    override fun say(imalId: Int, message: String, id: Int): ComResult<String> {
        logd("评论 imalId {} userId{}", imalId, id)
        return try {
            val d = imageDao.say(imalId, message, id)
            if (d > 0) {
                logd("评论成功")
                ComResult.success()
            } else {
                loge("评论错误 unknown")
                ComResult.fail()
            }
        } catch (e: Exception) {
            loge("评论错误 {}", e)
            ComResult.fail()
        }
    }

    override fun getComments(imalId: Int, pageSize: Int, index: Int): ComResult<Pages<SayVo>> {
        logd("获取图片评论 commentId:{}", imalId)
        return try {
            val total = imageDao.getCommentCount(imalId)
            val offset = pageSize * (index - 1)
            val sayVos = imageDao.getComments(imalId, pageSize, offset)
            val pages = Pages<SayVo>().apply {
                records = sayVos
                this.total = total
                this.index = index
                this.size = pageSize
            }
            logd("获取图片评论：成功")
            ComResult.success(pages)
        } catch (e: Exception) {
            loge("获取图片评论：失败 {}", e)
            ComResult.fail()
        }
    }

    override fun deleteComment(commentId: Int, id: Int): ComResult<String> {
        logd("删除评论 commentId userId{}", commentId, id)
        return try {
            val sayVo = imageDao.getCommentById(commentId) ?: return ComResult.er("say dose not exits")
            if (sayVo.userId != id) {
                logd("删除评论：失败 没权限")
                return ComResult.noper()
            }
            imageDao.deleteCommentById(commentId)
            ComResult.success()
        } catch (e: Exception) {
            loge("删除评论：失败 {}", e)
            ComResult.fail()
        }
    }
}