package com.example.hwarchdemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostListDao {

    @Query("SELECT COUNT(*) FROM Post")
    fun countPosts(): Int

    @Query("SELECT * FROM Post ORDER BY createdAt desc")
    fun getPosts(): Flow<List<Post>>

    @Insert
    fun storePosts(vararg posts: Post)

}