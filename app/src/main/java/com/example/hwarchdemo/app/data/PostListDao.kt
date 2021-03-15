package com.example.hwarchdemo.app.data

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
    fun storePost(post: Post)

    @Insert
    fun storePosts(posts: List<Post>)

}
