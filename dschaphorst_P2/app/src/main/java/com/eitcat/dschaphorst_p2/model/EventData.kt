package com.eitcat.dschaphorst_p2.model

import java.time.LocalDate
import java.util.*

data class EventData(
    val title: String,
    val category: String? = null,
    val date: LocalDate,
    val description: String? = null
)
