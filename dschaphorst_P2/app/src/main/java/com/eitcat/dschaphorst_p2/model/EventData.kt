package com.eitcat.dschaphorst_p2.model

import java.time.LocalDateTime

data class EventData(
    val title: String,
    val category: String? = null,
    val date: LocalDateTime,
    val description: String? = null
)
