package com.example.hwarchdemo.presentation

enum class UserWarning {
    HAS_WARNING,
    NONE
}

abstract class PostUiModel(val userId: Int)

class PostBanned(userId: Int) : PostUiModel(userId)
class PostRegular(val title: String, val body: String, val hasWarning: UserWarning, userId: Int) : PostUiModel(userId)