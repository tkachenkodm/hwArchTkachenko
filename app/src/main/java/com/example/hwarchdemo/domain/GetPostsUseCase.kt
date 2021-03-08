package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.PostsRepository
import com.example.hwarchdemo.presentation.PostUiMapper
import com.example.hwarchdemo.presentation.PostUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postUiMapper: PostUiMapper
) {
    fun execute(): Flow<List<PostUiModel>> {
        return postsRepository.getPosts().map {
            postUiMapper.map(it)
        }
    }
}