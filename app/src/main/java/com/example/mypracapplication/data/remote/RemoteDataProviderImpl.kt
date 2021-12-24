package com.example.mypracapplication.data.remote

import android.util.Log
import com.example.mypracapplication.data.dto.Post
import com.example.mypracapplication.data.dto.Result
import com.example.mypracapplication.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit

class RemoteDataProviderImpl(private val retrofit: Retrofit) : RemoteDataProvider {

    override suspend fun getPosts(): Result<List<Post>> {
        val postService = retrofit.create(ApiService::class.java);
        return getResponse(
            request = { postService.getPostsList() }
        )
    }


    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                if (result.code() == 404) {
                    return Result.error("Service Not Active try after some time", null)
                } else if (result.code() == 500) {
                    return Result.error("Server is down", null)
                }
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(
                    errorResponse?.message ?: "Api failing try after some time", errorResponse
                )
            }
        } catch (e: Throwable) {
            Result.error("Api failing try after some time", null)
        }
    }

}