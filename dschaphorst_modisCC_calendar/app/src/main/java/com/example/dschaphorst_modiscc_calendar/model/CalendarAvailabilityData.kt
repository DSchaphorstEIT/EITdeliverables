package com.example.dschaphorst_modiscc_calendar.model


import com.google.gson.annotations.SerializedName

data class CalendarAvailabilityData(
    @SerializedName("calendar")
    val calendarData: List<CalendarData?>?
) {
    fun findCalendarByType(type: String): CalendarData?{
        var result: CalendarData? = null
        calendarData?.forEach {
            if(it?.type?.lowercase() == type.lowercase()){
                result = it
            }
        }
        return result
    }
}