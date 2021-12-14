package io.neoattitude.defio.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun convertLongToDate(date: Long, pattern: String): String {
        val format = SimpleDateFormat(pattern, Locale.US)
        return format.format(Date(date))
    }
    fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("EEE", Locale.US)
        val d = Date()
        return sdf.format(d)
    }
}

