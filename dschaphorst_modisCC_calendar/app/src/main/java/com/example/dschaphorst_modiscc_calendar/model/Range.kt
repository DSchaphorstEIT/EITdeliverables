package com.example.dschaphorst_modiscc_calendar.model


import com.google.gson.annotations.SerializedName

data class Range(
    @SerializedName("end")
    val end: String?,
    @SerializedName("start")
    val start: String?,
    @SerializedName("weekday")
    val weekday: String?
)