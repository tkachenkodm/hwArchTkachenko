package com.example.hwarchdemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostListDao {
    companion object {
        const val startingIndex = 0
    }

    @Query("SELECT COUNT(*) FROM Post")
    fun countPosts(): Int

    @Query("SELECT * FROM Post ORDER BY createdAt desc")
    fun getPosts(): List<Post>

    @Insert
    fun storePosts(vararg posts: Post)

}