package com.example.kotlinflow.utils

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.MainActivity
import com.example.kotlinflow.R
import com.example.kotlinflow.Utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UtilActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    val list = mutableListOf(1)
    private lateinit var listFlow: Flow<Int>
    private lateinit var fixedFlow: Flow<String>
    private lateinit var lambdaFlow: Flow<Int>

    @InternalCoroutinesApi
    private lateinit var channelFlow: Flow<Int>


    @InternalCoroutinesApi
    override fun setContentView(view: View?) {
        super.setContentView(view)
        listFlow = list.asFlow()
        fixedFlow = flowOf("Vijay", "Siya", "Sonali", "Sonawane")
        lambdaFlow = flow {
            (1..10).forEach {
                emit(it)
            }
        }
        channelFlow = channelFlow {
            (1..10).forEach {
                send(it)
            }
        }

        createListFlow()
        traverseAFixedFlow()
        traverseLambdaFlow()
        traverseChannelFlow()
    }

    @InternalCoroutinesApi
    private fun traverseChannelFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            channelFlow.collect {
                Log.i(TAG, "ch $it")
            }
        }
    }

    private fun traverseLambdaFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            lambdaFlow.collect {
                Log.i(TAG, "$it")
            }
        }
    }

    private fun traverseAFixedFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            fixedFlow.collect { item ->
                Log.i(TAG, "{$item}")
            }
        }
    }


    private fun createListFlow() {
        findViewById<Button>(R.id.fixFlow).apply {
            setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.i(TAG, Thread.currentThread().name)
                    listFlow.collect { item ->
                        Log.i(TAG, Thread.currentThread().name)
                        Log.i(TAG, "{Emiited number $item}")
                    }
                }
            }
        }
        findViewById<Button>(R.id.fixFlowChange).apply {
            setOnClickListener {
                val newNumber = Utils.rand(1, 100)
                Log.i(TAG, "{ Added new number $newNumber}")
                list.add(newNumber)
            }
        }
    }
}