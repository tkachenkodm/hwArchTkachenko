package com.example.hwarchdemo.getposts.ui

import com.example.hwarchdemo.getposts.domain.PostModel
import com.example.hwarchdemo.getposts.domain.UserStatus
import javax.inject.Inject

class PostUiMapper @Inject constructor() {
    fun map(postList: List<PostModel>): List<PostUiModel>{
        return postList.map { post ->
            when (post.status) {
                UserStatus.BANNED -> PostBanned(post.userId, post.id)
                UserStatus.WARNED -> PostRegular(post.title, post.body, true, post.userId, post.id)
                UserStatus.NONE -> PostRegular(post.title, post.body, false, post.userId, post.id)
            }
        }
    }

}