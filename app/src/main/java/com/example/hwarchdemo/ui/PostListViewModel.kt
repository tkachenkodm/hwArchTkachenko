package com.example.hwarchdemo.ui

import androidx.lifecycle.ViewModel
import com.example.hwarchdemo.domain.CreateNewPostUseCase
import com.example.hwarchdemo.domain.GetPostsUseCase
import com.example.hwarchdemo.presentation.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val createNewPostUseCase: CreateNewPostUseCase
) : ViewModel() {

    fun getPosts(): Observable<List<PostUiModel>> {
        return getPostsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createNewPost(title: String, body: String): Single<Boolean> {
        return createNewPostUseCase.execute(title, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}