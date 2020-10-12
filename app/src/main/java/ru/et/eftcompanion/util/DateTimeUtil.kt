package ru.et.eftcompanion.util

import java.text.SimpleDateFormat

class DateTimeUtil {

    fun parseLongDateToString(long: Long?): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        return dateFormat.format(long)
    }
}