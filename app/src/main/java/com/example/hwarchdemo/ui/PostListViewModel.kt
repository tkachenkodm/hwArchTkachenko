package com.example.hwarchdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hwarchdemo.domain.CreateNewPostUseCase
import com.example.hwarchdemo.domain.GetPostsUseCase
import com.example.hwarchdemo.domain.PostModel
import com.example.hwarchdemo.presentation.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val createNewPostUseCase: CreateNewPostUseCase
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData = _postsLiveData as LiveData<List<PostUiModel>>


    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostsUseCase.execute()
            withContext(Dispatchers.Main) {
                _postsLiveData.value = result
            }
        }
    }

    fun createNewPost(title: String, body: String, block: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = createNewPostUseCase.execute(title, body)
            if (result) {
                getPosts()
            }
            withContext(Dispatchers.Main) {
                block(result)
            }
        }
    }

}