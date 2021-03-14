package com.example.hwarchdemo.getposts.domain

data class PostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val status: UserStatus
)
