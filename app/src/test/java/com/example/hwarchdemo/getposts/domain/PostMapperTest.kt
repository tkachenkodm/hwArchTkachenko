package com.example.hwarchdemo.getposts.domain

import com.example.hwarchdemo.app.data.Post
import com.example.hwarchdemo.getposts.data.UserInfoRepository
import com.example.hwarchdemo.testUtils.generateRandomTestInt
import com.example.hwarchdemo.testUtils.generateRandomTestString
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PostMapperTest {

    @TestFactory
    fun `postMapper works as intended`(): List<DynamicTest> {
        val bannedUser = generateRandomTestInt()
        val warnedUser = generateRandomTestInt()
        val mockUserInfoRepository = mockk<UserInfoRepository>() {
            every {
                getBannedUsers()
            } returns listOf(bannedUser)
            every {
                getWarnedUsers()
            } returns listOf(warnedUser)
        }

        val postMapper = PostMapper(mockUserInfoRepository)

        return listOf(
            Post(
                generateRandomTestInt(),
                generateRandomTestInt(),
                generateRandomTestString(5),
                generateRandomTestString(15)
            ) to UserStatus.NONE,

            Post(
                warnedUser,
                generateRandomTestInt(),
                generateRandomTestString(5),
                generateRandomTestString(15)
            ) to UserStatus.WARNED,

            Post(
                bannedUser,
                generateRandomTestInt(),
                generateRandomTestString(5),
                generateRandomTestString(15)
            ) to UserStatus.BANNED
        ).map { (post, status) ->
            DynamicTest.dynamicTest("Post user has to have status: $status") {
                postMapper.map(listOf(post)).first().status shouldBe status
            }
        }
    }


}