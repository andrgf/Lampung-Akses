package com.example.newsapp.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun dateFormat(isDateFormat: String): String {
    val dateTime = ZonedDateTime.parse(isDateFormat)
    val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    return dateFormatter.format(dateTime)
}