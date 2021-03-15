package com.example.hwarchdemo.app.data

import com.example.hwarchdemo.getposts.data.PostsService
import com.example.hwarchdemo.getposts.domain.PostMapper
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test


@FlowPreview
@ExperimentalCoroutinesApi
internal class PostsRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun `downloaded posts are cached on first app launch`() {
        val mockPostListDao = mockk<PostListDao>(relaxed = true) {
            every {
                countPosts()
            } returns 0
        }
        val mockPostsService = mockk<PostsService>(relaxed = true) {
            coEvery {
                getPosts()
            } returns listOf()
        }
        val mockPostMapper = mockk<PostMapper>(relaxed = true)

        val postsRepository =
            PostsRepository(mockPostListDao, mockPostsService, mockPostMapper, testDispatcher)

        testDispatcher.runBlockingTest {
            postsRepository.getPosts().collect { }
        }

        coVerifyOrder {
            mockPostsService.getPosts()
            mockPostListDao.storePosts(any())
        }

        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `posts are not re-downloaded on subsequent launches`() {
        val mockPostListDao = mockk<PostListDao>(relaxed = true) {
            every {
                countPosts()
            } returns 1
        }
        val mockPostsService = mockk<PostsService>(relaxed = true) {
            coEvery {
                getPosts()
            } returns listOf()
        }
        val mockPostMapper = mockk<PostMapper>(relaxed = true)

        val postsRepository =
            PostsRepository(mockPostListDao, mockPostsService, mockPostMapper, testDispatcher)

        testDispatcher.runBlockingTest {
            postsRepository.getPosts().collect { }
        }

        coVerify {
            mockPostsService wasNot Called
        }

        testDispatcher.cleanupTestCoroutines()
    }
}
