package com.timrashard.weathr.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
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

    fun formatTime(time: String, format: String): String {
        val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val date = inputFormat.parse(time)

        val outputFormat = SimpleDateFormat(format, Locale.getDefault())

        return outputFormat.format(date ?: Date())
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

    fun convertEpochToLocalTime(epoch: Long, zoneId: ZoneId = ZoneId.systemDefault()): String {
        val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), zoneId)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return localDateTime.format(formatter)
    }
}