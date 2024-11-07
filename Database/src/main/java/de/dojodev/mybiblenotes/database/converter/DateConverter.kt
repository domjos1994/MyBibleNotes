package de.dojodev.mybiblenotes.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    private val format = "yyyy-MM-dd HH:mm:ss"

    @TypeConverter
    fun fromDate(value: Date): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(value)
    }

    @TypeConverter
    fun fromString(value: String): Date {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.parse(value) ?: Date()
    }
}