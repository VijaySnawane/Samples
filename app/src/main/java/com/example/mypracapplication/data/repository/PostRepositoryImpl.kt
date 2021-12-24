package com.example.mypracapplication.data.repository

import com.example.mypracapplication.data.dto.Post
import com.example.mypracapplication.data.dto.Result
import com.example.mypracapplication.data.local.PostDao
import com.example.mypracapplication.data.remote.ApiService
import com.example.mypracapplication.data.remote.RemoteDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: RemoteDataProvider,
    private val postDao: PostDao
) : PostRepository {
    override suspend fun getPosts(): Flow<Result<List<Post>>?> {
        return flow {
            emit(Result.loading(null))
            val result = postRemoteDataSource.getPosts()
            if (result.status == Result.Status.SUCCESS) {
                result?.data?.let {
                    postDao.deleteAll(it)
                    postDao.insertAll(it)
                }
                emit(Result.success(result.data))
            } else if (result.status == Result.Status.ERROR) {
                emit(result)
            }
        }.flowOn(Dispatchers.IO)
    }
}