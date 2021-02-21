package com.example.hwarchdemo.data

import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}