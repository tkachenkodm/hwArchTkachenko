package com.example.hwarchdemo.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Post(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
    val createdAt: String? = Date().toString()
)
