package com.example.hwarchdemo.data

import com.example.hwarchdemo.domain.PostMapper
import com.example.hwarchdemo.domain.PostModel
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postListDao: PostListDao,
    private val postsService: PostsService,
    private val postMapper: PostMapper
) {

    fun getPosts(): List<PostModel> {
        if (postListDao.countPosts() == 0) {
            val posts = postsService.getPosts().execute().body() ?: listOf()
            postListDao.storePosts(*posts.toTypedArray())
        }

        return postMapper.map(postListDao.getPosts())
    }

    fun createNewPost(title: String, body: String) {
        val lastPostId = postListDao.getPosts().maxByOrNull(Post::id)?.id ?: PostListDao.startingIndex

        postListDao.storePosts(
            Post(
                userId,
                lastPostId + 1,
                title,
                body
            )
        )
    }

    companion object {
        private const val userId = 11
    }
}