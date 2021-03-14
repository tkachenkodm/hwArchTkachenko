package com.example.hwarchdemo.createpost.domain

import com.example.hwarchdemo.app.data.PostsRepository
import com.example.hwarchdemo.createpost.data.ForbiddenWordsRepository
import com.example.hwarchdemo.testUtils.generateRandomTestString
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

@ExperimentalCoroutinesApi
internal class CreateNewPostUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @TestFactory
    fun `post verification works as intended`(): List<DynamicTest> {
        val mockPostsRepository = mockk<PostsRepository>(relaxed = true)

        val forbiddenWord = generateRandomTestString(12)
        val mockForbiddenWordsRepository = mockk<ForbiddenWordsRepository>() {
            every {
                getForbiddenWords()
            } returns listOf(forbiddenWord)
        }

        val createNewPostUseCase =
            CreateNewPostUseCase(mockPostsRepository, mockForbiddenWordsRepository)

        return listOf(
            Pair("        ", "       ") to false,
            Pair(generateRandomTestString(2), generateRandomTestString(5)) to false,
            Pair(generateRandomTestString(3), generateRandomTestString(4)) to false,
            Pair(forbiddenWord, generateRandomTestString(5)) to false,
            Pair(generateRandomTestString(51), generateRandomTestString(501)) to false,
            Pair(generateRandomTestString(3), generateRandomTestString(5)) to true,
        ).map { (post, expectedResult) ->
            DynamicTest.dynamicTest(
                "When creating post with " +
                        "title: ${post.first}, and body: ${post.second} " +
                        "verification should return $expectedResult"
            ) {
                val (title, body) = post
                with(testDispatcher) {
                    runBlockingTest {
                        createNewPostUseCase.execute(title, body) shouldBe expectedResult
                    }
                    cleanupTestCoroutines()
                }
            }
        }
    }
}