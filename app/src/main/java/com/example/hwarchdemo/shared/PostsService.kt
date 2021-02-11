package com.example.hwarchdemo.shared

import com.example.hwarchdemo.data.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}

/*class PostsService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPosts() : Call<List<Post>> {
        return retrofit.create(GetPosts::class.java).getPosts()
    }
}*/