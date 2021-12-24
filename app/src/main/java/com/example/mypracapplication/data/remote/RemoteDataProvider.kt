package com.example.mypracapplication.data.remote

import com.example.mypracapplication.data.dto.Post
import com.example.mypracapplication.data.dto.Result

interface RemoteDataProvider {
    suspend fun getPosts(): Result<List<Post>>
}