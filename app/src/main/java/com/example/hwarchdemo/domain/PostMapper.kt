package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.Post
import javax.inject.Inject

enum class UserStatus {
    NONE,
    BANNED,
    WARNED
}

class PostMapper @Inject constructor() {
    fun map(postList: List<Post>): List<PostModel> {
        return postList.map { post ->
            PostModel(post.userId, post.id, post.title, post.body, UserStatus.NONE)
        }
    }
}