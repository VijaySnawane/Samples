package com.example.mypracapplication.presentation.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypracapplication.data.dto.Post
import com.example.mypracapplication.data.dto.Result
import com.example.mypracapplication.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {

    private var _postList = MutableLiveData<Result<List<Post>>>()
    val postList = _postList

    init {
        fetchPost()
    }

    private fun fetchPost() {
        viewModelScope.launch {
            postRepository.getPosts().collect {
                _postList.value = it
            }
        }
    }
}