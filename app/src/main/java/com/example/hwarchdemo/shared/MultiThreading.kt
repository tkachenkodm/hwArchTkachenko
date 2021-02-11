package com.example.hwarchdemo.shared

import android.content.Context
import android.os.Handler

class MultiThreading(context: Context) {
    private val mainHandler = Handler(context.mainLooper)

    fun <T> async(operation: () -> T): AsyncOperation<T> {
        return AsyncOperation(
            operation = operation,
            mainHandler = mainHandler,
            threadCreation = ::createThread
        )
    }

    private fun createThread(runnable: Runnable) : Thread {
        return Thread(runnable).apply(Thread::start)
    }
}