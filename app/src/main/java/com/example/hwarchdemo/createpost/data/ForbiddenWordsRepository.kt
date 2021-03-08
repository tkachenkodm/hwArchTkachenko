package com.example.hwarchdemo.createpost.data

import android.content.Context
import com.example.hwarchdemo.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ForbiddenWordsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    fun getForbiddenWords(): List<String> {
        return forbiddenWords.map {
            context.getString(it)
        }
    }

    private companion object {
        val forbiddenWords = listOf(
            R.string.ad_word,
            R.string.item_word,
            R.string.buy_word,
        )
    }
}