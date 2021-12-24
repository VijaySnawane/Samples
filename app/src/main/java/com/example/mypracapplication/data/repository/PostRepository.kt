package com.example.mypracapplication.data.repository

import com.example.mypracapplication.data.dto.Post
import com.example.mypracapplication.data.dto.Result
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(): Flow<Result<List<Post>>?>
}
