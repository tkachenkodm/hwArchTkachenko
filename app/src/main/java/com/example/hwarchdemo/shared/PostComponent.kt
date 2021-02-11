package com.example.hwarchdemo.shared

import android.content.Context
import com.example.hwarchdemo.data.PostsRepository
import com.example.hwarchdemo.domain.PostMapper
import com.example.hwarchdemo.presentation.PostPresenter
import com.example.hwarchdemo.presentation.PostUiMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostComponent {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun createPresenter(context: Context): PostPresenter {
        return PostPresenter(
            postsRepository = PostsRepository(
                multithreading = MultiThreading(context),
                postsService = createService(),
                postMapper = PostMapper()
            ),
            postUiMapper = PostUiMapper()
        )
    }


    private fun createService(): PostsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsService::class.java)
    }
}