package com.example.hwarchdemo.ui

import androidx.lifecycle.*
import com.example.hwarchdemo.domain.CreateNewPostUseCase
import com.example.hwarchdemo.domain.GetPostsUseCase
import com.example.hwarchdemo.presentation.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val createNewPostUseCase: CreateNewPostUseCase
) : ViewModel() {

    val postsLiveData: LiveData<List<PostUiModel>> =
        getPostsUseCase.execute().asLiveData(viewModelScope.coroutineContext)

    fun createNewPost(title: String, body: String, block: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = createNewPostUseCase.execute(title, body)
            block(result)
        }
    }

}