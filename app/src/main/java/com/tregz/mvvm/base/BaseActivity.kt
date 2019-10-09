package com.tregz.mvvm.base

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, State.CREATED.name)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, State.STARTED.name)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, State.RESUMED.name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, State.MENU_CREATED.name)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, State.PAUSED.name)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, State.STOPPED.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, State.DESTROYED.name)
    }

    private enum class State {
        CREATED,
        STARTED,
        RESUMED,
        MENU_CREATED,
        PAUSED,
        STOPPED,
        DESTROYED
    }

    companion object {
        protected var TAG = BaseActivity::class.java.simpleName
    }
}
