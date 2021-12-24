package com.example.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlin.properties.Delegates

class Person(_firstName1: String) : BaseObservable() {
    @get:Bindable
    var firstName1 by Delegates.observable(_firstName1)
    { _, _, _ ->
        notifyPropertyChanged(BR.firstName1)
    }

}
