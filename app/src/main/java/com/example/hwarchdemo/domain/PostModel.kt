package com.example.hwarchdemo.domain

data class PostModel(val userId: Int, val id: Int, val title: String, val body: String, var status: UserStatus) {}