package com.example.hwarchdemo.data

import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}