package com.timrashard.weathr.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeUtils {

    fun formatDate(date: String, format: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val outputFormat = try {
            SimpleDateFormat(format, Locale.getDefault())
        } catch (e: IllegalArgumentException) {
            return "Invalid format"
        }

        return try {
            val parsedDate = inputFormat.parse(date)
            parsedDate?.let { outputFormat.format(it) } ?: "Invalid date"
        } catch (e: Exception) {
            "Invalid date"
        }
    }

    fun formatDateWithDayName(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())

        return try {
            val parsedDate = inputFormat.parse(date)
            parsedDate?.let { outputFormat.format(it) } ?: "Invalid date"
        } catch (e: Exception) {
            "Invalid date"
        }
    }

    fun getDayName(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        return try {
            val parsedDate = inputFormat.parse(date)
            parsedDate?.let { outputFormat.format(it) } ?: "Invalid date"
        } catch (e: Exception) {
            "Invalid date"
        }
    }

    fun getLocalizedTodayName(): String {
        val todayName = when (Locale.getDefault().language) {
            "tr" -> "Bugün"
            else -> "Today"
        }
        return todayName
    }
}