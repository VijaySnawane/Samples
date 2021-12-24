package com.example.databinding

import androidx.databinding.ObservableInt

import androidx.databinding.ObservableField




data class User(
    var firstName: ObservableField<String> = ObservableField<String>(),
    val lastName: ObservableField<String> = ObservableField<String>(),
    val age: ObservableInt = ObservableInt()
)
