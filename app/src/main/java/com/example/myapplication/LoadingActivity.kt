package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.os.Handler

class LoadingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({ finish() }, 2000)
    }
}