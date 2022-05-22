package com.qiao.album.dao

import com.qiao.album.pojo.vo.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserDao {
    fun addUser(user: User): Int

    fun selectUserByName(name: String): User?

    fun selectUserById(id: Int): User?
}