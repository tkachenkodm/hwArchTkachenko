package com.example.hwarchdemo.domain

import android.util.Log
import com.example.hwarchdemo.data.PostsRepository
import com.example.hwarchdemo.data.UserInfoRepository
import com.example.hwarchdemo.presentation.PostUiMapper
import com.example.hwarchdemo.presentation.PostUiModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val userInfoRepository: UserInfoRepository,
    private val postUiMapper: PostUiMapper
) {
    fun execute(): Observable<List<PostUiModel>> {
        return postsRepository.getPosts().map {
            it.forEach { post ->
                when (post.userId) {
                    in userInfoRepository.getBannedUsers() -> post.status = UserStatus.BANNED
                    in userInfoRepository.getWarnedUsers() -> post.status = UserStatus.WARNED
                }
            }
            postUiMapper.map(it)
        }

    }
}