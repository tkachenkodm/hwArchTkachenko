package com.example.hwarchdemo.getposts.data

import com.example.hwarchdemo.app.data.Post
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}
