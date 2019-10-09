package com.tregz.mvvm.data.item

import android.util.Log
import com.tregz.mvvm.R
import com.tregz.mvvm.core.date.DateUtil
import java.util.*

data class ItemApple(val ripe: Date?) {

    var color: Int = R.color.colorPrimary
        set(value) {
            if (enumValues<Color>().any { it.resource == value }) field = value
            else Log.e(TAG, "Error: this color ($value) is not supported")
        }

    private enum class Color(val resource: Int) {
        GREEN(R.color.colorPrimary),
        GREEN_DARK(R.color.colorPrimaryDark),
        PINK(R.color.colorAccent)
    }

    fun edible(): Boolean {
        val bestBefore = 1 // one month
        return ripe?.let {
            Date().before(DateUtil.addMonth(it, bestBefore))
        } ?: false
    }

    companion object {
        private val TAG: String = ItemApple::class.java.simpleName
    }
}