package com.example.mypracapplication.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(val name: String)
{
    @PrimaryKey(autoGenerate = true)
    var idKey: Int = 0
}
