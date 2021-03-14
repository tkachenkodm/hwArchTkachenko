package com.example.hwarchdemo.testUtils

fun generateRandomTestString(length: Int): String {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length).map { kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}

fun generateRandomTestInt(): Int {
    return kotlin.random.Random.nextInt(0, Int.MAX_VALUE)
}