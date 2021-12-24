package com.example.kotlinflow.Utils

import kotlin.random.Random

object Utils {
    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        val rand = Random(System.nanoTime())
        return (start..end).random(rand)
    }
}