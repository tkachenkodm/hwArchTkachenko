package com.example.hwarchdemo.getposts.ui

import com.example.hwarchdemo.getposts.domain.PostModel
import com.example.hwarchdemo.getposts.domain.UserStatus
import com.example.hwarchdemo.testUtils.generateRandomTestInt
import com.example.hwarchdemo.testUtils.generateRandomTestString
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.matchers.types.shouldNotBeSameInstanceAs
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PostUiMapperTest {

    @TestFactory
    fun `postUiMapper maps non-banned posts correctly`(): List<DynamicTest> {
        val postUiMapper = PostUiMapper()

        return listOf(
            PostModel(
                generateRandomTestInt(),
                generateRandomTestInt(),
                generateRandomTestString(5),
                generateRandomTestString(15),
                UserStatus.NONE
            ) to false,

            PostModel(
                generateRandomTestInt(),
                generateRandomTestInt(),
                generateRandomTestString(5),
                generateRandomTestString(15),
                UserStatus.WARNED
            ) to true,
        ).map { (post, status) ->
            DynamicTest.dynamicTest("Post should have user status $status") {
                val mappedPost = postUiMapper.map(listOf(post))

                mappedPost.first().shouldBeInstanceOf<PostRegular> {
                    it.hasWarning shouldBe status
                }
            }
        }
    }

    @Test
    fun `postUiMapper maps banned posts correctly`() {
        val postUiMapper = PostUiMapper()

        postUiMapper.map(
            listOf(
                PostModel(
                    generateRandomTestInt(),
                    generateRandomTestInt(),
                    generateRandomTestString(5),
                    generateRandomTestString(15),
                    UserStatus.BANNED
                )
            )
        ).first().shouldBeInstanceOf<PostBanned>()
    }

}