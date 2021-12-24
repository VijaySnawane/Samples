package com.example.mypracapplication.data.local

import androidx.room.*
import com.example.mypracapplication.data.dto.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM post order by name DESC")
    fun getAll(): List<Post>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Post>)

    @Delete
    fun delete(movie: Post)

    @Delete
    fun deleteAll(movie: List<Post>)
}

