package com.example.hwarchdemo.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    fun getPosts(): Single<List<Post>>
}