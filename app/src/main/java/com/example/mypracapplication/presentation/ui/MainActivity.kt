package com.example.mypracapplication.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mypracapplication.R
import com.example.mypracapplication.data.dto.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.postList.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> Log.i("", "");
                Result.Status.SUCCESS -> Log.i("", "");
                Result.Status.ERROR -> Log.i("", "");
            }
        })
    }
}
