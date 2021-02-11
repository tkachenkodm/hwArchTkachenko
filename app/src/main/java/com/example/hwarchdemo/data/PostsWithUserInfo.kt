package com.example.hwarchdemo.data

data class PostsWithUserInfo(val posts: List<Post>, val bannedUsers: List<Int>, val warnedUsers: List<Int>) {
}