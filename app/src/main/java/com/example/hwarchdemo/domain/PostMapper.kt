package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.Post
import com.example.hwarchdemo.data.UserInfoRepository
import javax.inject.Inject

enum class UserStatus {
    NONE,
    BANNED,
    WARNED
}

class PostMapper @Inject constructor(private val userInfoRepository: UserInfoRepository) {
    fun map(postList: List<Post>): List<PostModel> {
        return postList.map { post ->
            when (post.userId) {
                in userInfoRepository.getBannedUsers() -> PostModel(
                    post.userId,
                    post.id,
                    post.title,
                    post.body,
                    UserStatus.BANNED
                )
                in userInfoRepository.getWarnedUsers() -> PostModel(
                    post.userId,
                    post.id,
                    post.title,
                    post.body,
                    UserStatus.WARNED
                )
                else -> PostModel(post.userId, post.id, post.title, post.body, UserStatus.NONE)
            }
        }
    }
}
