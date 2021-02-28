package com.example.hwarchdemo.domain

import com.example.hwarchdemo.data.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class CreateNewPostUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    fun execute(title: String, body: String): Single<Boolean> {
        if (title.length in MIN_TITLE_LENGTH..MAX_TITLE_LENGTH &&
            body.length in MIN_BODY_LENGTH..MAX_BODY_LENGTH
        ) {
            if (!forbiddenWords.any {
                    title.contains(it, true)
                }
            ) {
                return postsRepository.createNewPost(title, body).andThen(
                    Single.just(true)
                )
            }
        }
        return Single.just(false)
    }

    companion object {
        private const val MIN_TITLE_LENGTH = 3
        private const val MAX_TITLE_LENGTH = 50
        private const val MIN_BODY_LENGTH = 5
        private const val MAX_BODY_LENGTH = 500

        private val forbiddenWords = listOf(
            "реклама",
            "товар",
            "куплю",
        )
    }
}