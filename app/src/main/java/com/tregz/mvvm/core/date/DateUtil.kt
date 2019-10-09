package com.tregz.mvvm.core.date

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun addMonth(then: Date, i: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = then
        calendar.add(Calendar.MONTH, i)
        return calendar.time
    }

    fun parse(date: String): Date? {
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
        } catch (e: ParseException) {
            e.message?.let { Log.e(DateUtil::class.java.simpleName, it) }
            null
        }
    }

}