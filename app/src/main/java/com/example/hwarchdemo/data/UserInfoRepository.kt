package com.example.hwarchdemo.data

class UserInfoRepository() {
    fun getBannedUsers(): List<Int> {
        return listOf(7)
    }

    fun getWarnedUsers(): List<Int> {
        return listOf(3, 4)
    }
}