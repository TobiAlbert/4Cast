package com.tobidaada.a4cast.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCityFromTimezone(timezone: String): String {
    val cityArray = timezone.split("/")
    return if (cityArray.size > 1) cityArray[1].replace("_", " ") else timezone
}

const val DAY_MONTH_DATE_PATTERN = "EEEE MMMM dd"
const val SHORT_DAY_PATTERN = "E"

fun formatDateFromUnixTime(time: Long, pattern: String = DAY_MONTH_DATE_PATTERN): String {
    val epochTime = time * 1000
    val date = Date(epochTime)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date).toString()
}