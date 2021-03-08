package com.example.hwarchdemo.getposts.domain

import com.example.hwarchdemo.app.data.PostsRepository
import com.example.hwarchdemo.getposts.ui.PostUiMapper
import com.example.hwarchdemo.getposts.ui.PostUiModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postUiMapper: PostUiMapper
) {
    @FlowPreview
    fun execute(): Flow<List<PostUiModel>> {
        return postsRepository.getPosts().map {
            postUiMapper.map(it)
        }
    }
}