package com.example.mypracapplication.di

import android.content.Context
import androidx.room.Room
import com.example.mypracapplication.common.Constant.Companion.BASE_URL
import com.example.mypracapplication.data.local.AppDataBase
import com.example.mypracapplication.data.local.PostDao
import com.example.mypracapplication.data.remote.ApiService
import com.example.mypracapplication.data.remote.RemoteDataProvider
import com.example.mypracapplication.data.remote.RemoteDataProviderImpl
import com.example.mypracapplication.data.repository.PostRepository
import com.example.mypracapplication.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "app.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRemoteDataProvider(
        retrofit: Retrofit,
    ): RemoteDataProvider {
        return RemoteDataProviderImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideRepo(
        remoteDataProvider: RemoteDataProvider,
        postDao: PostDao
    ): PostRepository {
        return PostRepositoryImpl(remoteDataProvider, postDao)
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDataBase): PostDao {
        return appDatabase.postDao()
    }

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor;
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}
