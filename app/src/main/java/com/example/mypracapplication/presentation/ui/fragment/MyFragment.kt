package com.example.mypracapplication.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypracapplication.R
import java.util.jar.Manifest

class MyFragment : PermissionFragment() {

    companion object {
        fun newInstance(): MyFragment {
            return MyFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!checkPermissions("android.permission.CAMERA")) {
            requestPermissions("android.permission.CAMERA")
        }
    }

    override fun permissionGranted() {
        Log.i("", "permissionGranted");
    }

  /*  override fun permissionDenied(permissions: Array<out String>?, grantResults: IntArray?) {
        Log.i("", "permissionGranted");
    }*/
}