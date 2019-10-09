package com.tregz.mvvm.core.read

import android.util.Log

object ReadUtil {
    private val TAG = ReadUtil::class.java.simpleName

    fun parse(input: String): Int? {
        return try {
            Integer.parseInt(input)
        } catch (e: NumberFormatException) {
            e.message?.let { Log.e(TAG, it) }
            null
        }
    }
}