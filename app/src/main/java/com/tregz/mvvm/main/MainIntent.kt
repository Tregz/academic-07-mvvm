package com.tregz.mvvm.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

class MainIntent : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Testing splash screen
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, MOCK_DELAY)
    }

    companion object {
        private const val MOCK_DELAY = 1000L
    }
}