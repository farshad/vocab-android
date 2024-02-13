package xyz.farshad.vocab.data.utils

import androidx.room.TypeConverter
import java.util.*

class LocaleConverter {
    @TypeConverter
    fun fromString(value: String?): Locale? {
        return value?.let {
            val parts = it.split("_")
            when (parts.size) {
                1 -> Locale(parts[0])
                2 -> Locale(parts[0], parts[1])
                else -> Locale(parts[0], parts[1], parts[2])
            }
        }
    }

    @TypeConverter
    fun localeToString(locale: Locale?): String? {
        return locale?.toString()
    }
}
