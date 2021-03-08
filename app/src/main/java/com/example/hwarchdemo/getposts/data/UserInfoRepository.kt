package com.example.hwarchdemo.getposts.data

import javax.inject.Inject

class UserInfoRepository @Inject constructor() {
    fun getBannedUsers(): List<Int> {
        return listOf(7)
    }

    fun getWarnedUsers(): List<Int> {
        return listOf(3, 4)
    }
}