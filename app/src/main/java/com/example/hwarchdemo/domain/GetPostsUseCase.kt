package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.PostsRepository
import com.example.hwarchdemo.data.UserInfoRepository
import com.example.hwarchdemo.presentation.PostUiMapper
import com.example.hwarchdemo.presentation.PostUiModel
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val userInfoRepository: UserInfoRepository,
    private val postUiMapper: PostUiMapper
) {
    fun execute(): List<PostUiModel> {
        val list = postsRepository.getPosts()
        list.forEach { post ->
            when(post.userId) {
                in userInfoRepository.getBannedUsers() -> post.status = UserStatus.BANNED
                in userInfoRepository.getWarnedUsers() -> post.status = UserStatus.WARNED
            }
        }

        return postUiMapper.map(list)
    }
}