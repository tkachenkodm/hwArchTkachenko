package com.example.hwarchdemo.presentation

import com.example.hwarchdemo.data.PostError
import com.example.hwarchdemo.domain.PostModel
import com.example.hwarchdemo.domain.UserStatus
import com.example.hwarchdemo.shared.Result

class PostUiMapper() {
    fun map(postResult: Result<List<PostModel>, PostError>): Result<List<PostUiModel>, String> {
        return postResult.mapSuccess { posts ->

            posts.map { post ->
                when (post.status) {
                    UserStatus.BANNED -> PostBanned(post.userId)
                    UserStatus.WARNED -> PostRegular(post.title, post.body, UserWarning.HAS_WARNING, post.userId)
                    UserStatus.NONE -> PostRegular(post.title, post.body, UserWarning.NONE, post.userId)
                }
            }

        }.mapError {
            "Network error occurred"
        }

    }
}