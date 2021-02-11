package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.PostError
import com.example.hwarchdemo.data.PostsWithUserInfo
import com.example.hwarchdemo.shared.Result

enum class UserStatus {
    NONE,
    BANNED,
    WARNED
}

class PostMapper {

    fun map(postResult: Result<PostsWithUserInfo, PostError>): Result<List<PostModel>, PostError> {
        return postResult.mapSuccess { postsData ->

            postsData.posts.map { post ->
                when(post.userId) {
                    in postsData.warnedUsers -> PostModel(post.userId, post.title, post.body, UserStatus.WARNED)
                    in postsData.bannedUsers -> PostModel(post.userId, post.title, post.body, UserStatus.BANNED)
                    else -> PostModel(post.userId, post.title, post.body, UserStatus.NONE)
                }
            }
        }
    }
}