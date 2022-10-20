package com.example.dschaphorst_modiscc_calendar.model


import com.google.gson.annotations.SerializedName

data class CalendarData(
    @SerializedName("label")
    val label: String?,
    @SerializedName("ranges")
    val ranges: List<Range?>?,
    @SerializedName("type")
    val type: String?
)