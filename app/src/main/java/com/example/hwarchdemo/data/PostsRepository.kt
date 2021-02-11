package com.example.hwarchdemo.data

import com.example.hwarchdemo.domain.PostMapper
import com.example.hwarchdemo.domain.PostModel
import com.example.hwarchdemo.shared.AsyncOperation
import com.example.hwarchdemo.shared.MultiThreading
import com.example.hwarchdemo.shared.PostsService
import com.example.hwarchdemo.shared.Result

enum class PostError {
    POSTS_NOT_LOADED
}

class PostsRepository(
    private val multithreading: MultiThreading,
    private val postsService: PostsService,
    private val postMapper: PostMapper
) {
    private val userInfoRepository = UserInfoRepository()

    fun getPosts(): AsyncOperation<Result<List<PostModel>, PostError>> {
        val bannedUsers = userInfoRepository.getBannedUsers()
        val warnedUsers = userInfoRepository.getWarnedUsers()


        val getAsync = multithreading.async<Result<PostsWithUserInfo, PostError>> {
            val posts = postsService.getPosts().execute().body() ?: return@async Result.error(
                PostError.POSTS_NOT_LOADED
            )

            return@async Result.success(
                PostsWithUserInfo(posts = posts, bannedUsers = bannedUsers, warnedUsers = warnedUsers)
            )
        }

        return getAsync.map(postMapper::map)
    }
}