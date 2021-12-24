package com.example.databinding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            //Picasso.get().load(url).into(view)
            Log.i("", "" + url + view)
        }
    }
}