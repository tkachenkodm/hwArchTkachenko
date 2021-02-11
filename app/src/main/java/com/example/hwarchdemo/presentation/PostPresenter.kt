package com.example.hwarchdemo.presentation

import com.example.hwarchdemo.data.PostsRepository
import com.example.hwarchdemo.shared.CancelableOperation
import com.example.hwarchdemo.shared.Result

interface PostsView {
    fun showPosts(posts: List<PostUiModel>)
    fun showError(error: String)
}

class PostPresenter(
    private val postsRepository: PostsRepository,
    private val postUiMapper: PostUiMapper
) {
    private var view: PostsView? = null
    private var cancelableOperation: CancelableOperation? = null


    fun attachView(postsView: PostsView) {
        view = postsView

        cancelableOperation = postsRepository.getPosts()
            .map(postUiMapper::map)
            .postOnMainThread(::showResult)
    }

    fun detachView() {
        view = null
        cancelableOperation?.cancel()
    }

    private fun showResult(result: Result<List<PostUiModel>, String>) {
        if (result.isError) {
            view?.showError(result.errorResult)
        } else {
            view?.showPosts(result.successResult)
        }
    }
}