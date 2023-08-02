package com.example.mooddiarycompose.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object MDDateFormatter {
    const val pattern = "dd-MM-yyyy"
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
}

fun LocalDate?.toFormattedString(): String {
    if (this == null) return ""
    return MDDateFormatter.formatter.format(this)
}

fun Long.milliToLocalDate(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(this/1000, (this % 1000 * 1000000).toInt(), ZoneOffset.UTC)
}