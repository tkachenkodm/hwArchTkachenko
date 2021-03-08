package com.example.hwarchdemo.getposts.ui

sealed class PostUiModel(val userId: Int, val id: Int)

class PostBanned(userId: Int, id: Int) : PostUiModel(userId, id)
class PostRegular(
    val title: String,
    val body: String,
    val hasWarning: Boolean,
    userId: Int,
    id: Int
) : PostUiModel(userId, id)