package com.example.dschaphorst_modiscc_calendar.model.domain

import com.example.dschaphorst_modiscc_calendar.model.Range
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class DateRange(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
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
            endDate = LocalDateTime.parse(it.end, inputDateFormat)
        )
    } ?: DateRange(LocalDateTime.MIN, LocalDateTime.MIN)
// TODO: This is not good null handling; however, creating the object with MIN values should
// TODO: trigger this object to not be sent to output, if the past check was re-enabled.