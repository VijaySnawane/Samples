package com.example.databinding

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mUser: Person


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mUser = Person("Vijay Sonawane")
        binding.person = mUser
        binding.mUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()) {
                    binding.tvLabel.setBackgroundColor(Color.RED)
                } else {
                    binding.tvLabel.setBackgroundColor(Color.YELLOW)
                }
                mUser.firstName1=p0.toString()
            }

        })
    }

    fun click(view: View) {
        mUser.firstName1=("111111111-->".plus((1..300).shuffled().last()))
    }
}