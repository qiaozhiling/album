package com.qiao.album.dao

import com.qiao.album.pojo.vo.Album
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface AlbumDao {
    fun newAlbum(album: Album): Int

    /**
     * 返回同一个用户是否有同名的相册
     */
    fun hasSameAlbum(album: Album): Int

    // 分页获取用户相册
    fun getAlbumByUserId(map: Map<String, Any>): ArrayList<Album>

    // 分页获取开放相册
    fun getOpenAlbum(map: Map<String, Int>): ArrayList<Album>

    /**
     * @param id 用户的id
     * @return 返回用户的总相册数量
     */
    fun getAlbumCount(id: Int): Int

    /**
     * 获取开放相册数量
     */
    fun getOpenAlbumCount(): Int

    fun deleteAlbum(album: Album): Int

    fun updateAlbum(album: Album): Int

    /**
     * @param album 用 owner 和 id判断
     * 返回这个相册是否是这个用户的
     * 即可不可以上传
     */
    fun hasRight(album: Album): Int

    fun getAlbumById(albumId: Int): Album?

    fun getCountByQuery(@Param("content") content: String): Int

    fun getAlbumByQuery(
        @Param("content") content: String,
        @Param("pageSize") pageSize: Int,
        @Param("offset") offset: Int
    ): ArrayList<Album>
}