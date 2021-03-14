package com.example.hwarchdemo.getposts.data

import androidx.room.Ignore
import javax.inject.Inject

@Suppress("MagicNumber")
class UserInfoRepository @Inject constructor() {
    fun getBannedUsers(): List<Int> {
        return listOf(7)
    }

    fun getWarnedUsers(): List<Int> {
        return listOf(3, 4)
    }
}