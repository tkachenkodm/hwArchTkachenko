package com.example.hwarchdemo.data

import com.example.hwarchdemo.domain.PostMapper
import com.example.hwarchdemo.domain.PostModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postListDao: PostListDao,
    private val postsService: PostsService,
    private val postMapper: PostMapper,
    private val ioDispatcher: CoroutineDispatcher
) {

    @FlowPreview
    fun getPosts(): Flow<List<PostModel>> {
        return flow {
            emit(postListDao.countPosts())
        }.flatMapConcat { count ->
            if (count == 0) {
                val posts = postsService.getPosts()
                postListDao.storePosts(*posts.toTypedArray())
            }
            postListDao.getPosts()
        }.map {
            postMapper.map(it)
        }.flowOn(ioDispatcher)
    }

    suspend fun createNewPost(title: String, body: String) =
        withContext(ioDispatcher) {
            postListDao.storePosts(
                Post(
                    userId,
                    postListDao.countPosts() + 1,
                    title,
                    body
                )
            )
        }

    companion object {
        private const val userId = 11
    }
}