package com.example.dschaphorst_modiscc_calendar.model.domain

import com.example.dschaphorst_modiscc_calendar.model.Range
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class DateRange(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val dayOfWeek: String
)

/**
 * Converter function from given JSON data to local domain type.
 * Assumptions made are that all JSON inputs are identical formatting to the given sample.
 */
fun Range?.mapToDateRange(): DateRange =
    this?.let {
        val inputDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")
        DateRange(
            startDate = LocalDateTime.parse(it.start, inputDateFormat),
            endDate = LocalDateTime.parse(it.end, inputDateFormat),
            dayOfWeek = when(it.weekday?.lowercase()){
                "mon" ->  "Monday"
                "tue" -> "Tuesday"
                "wed" -> "Wednesday"
                "thu" -> "Thursday"
                "fri" -> "Friday"
                "sat" -> "Saturday"
                "sun" -> "Sunday"
                else -> ""
            }
        )
    } ?: DateRange(LocalDateTime.MIN, LocalDateTime.MIN, "")
// TODO: This is not good null handling; however, should trigger this object to not be sent to output.