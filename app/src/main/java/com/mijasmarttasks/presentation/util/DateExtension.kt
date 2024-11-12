package com.mijasmarttasks.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.isSameDay(other: Calendar): Boolean {
    val calendar = Calendar.getInstance().apply { time = this@isSameDay }
    return calendar.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            calendar.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
}

fun String.toDate(format: String = "yyyy-MM-dd"): Date? {
    return try {
        SimpleDateFormat(format, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}