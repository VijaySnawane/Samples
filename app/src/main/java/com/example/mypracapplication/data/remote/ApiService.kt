package com.example.mypracapplication.data.remote

import com.example.mypracapplication.data.dto.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("coins")
    suspend fun getPostsList() : Response<List<Post>>
}

