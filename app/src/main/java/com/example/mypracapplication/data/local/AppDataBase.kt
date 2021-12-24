package com.example.mypracapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypracapplication.data.dto.GenreConverters
import com.example.mypracapplication.data.dto.Post

@Database(entities = [Post::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun postDao(): PostDao
}