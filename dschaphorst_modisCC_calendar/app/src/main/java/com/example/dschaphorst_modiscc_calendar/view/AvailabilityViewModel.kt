package com.example.dschaphorst_modiscc_calendar.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dschaphorst_modiscc_calendar.model.CalendarAvailabilityData
import com.example.dschaphorst_modiscc_calendar.model.Range
import com.example.dschaphorst_modiscc_calendar.model.domain.mapToDateRange
import com.example.dschaphorst_modiscc_calendar.util.FileHandler
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

private const val TAG = "AvailabilityViewModel"
class AvailabilityViewModel(application: Application) : AndroidViewModel(application) {

    private val _input = MutableLiveData<List<String>>()
    val input: LiveData<List<String>> get() = _input

    private val calendarAvailabilityData by lazy {
        val jsonString = FileHandler.getJsonFromAssets(application.applicationContext, "calendars.json")
        Gson().fromJson(jsonString, CalendarAvailabilityData::class.java)
    }

    fun findAvailableOrderingTimes(
        a: Int = 42, b: Int = 10, c: Int = 15, d: String = "pickup"
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val dateStrings: MutableList<String> = mutableListOf()
            val ranges: List<Range?> = calendarAvailabilityData.findCalendarByType(d)?.ranges ?: emptyList()
            ranges.forEach {
                var interval = it.mapToDateRange().startDate.plusMinutes(a.toLong())
                while (
/*                    interval.isAfter(LocalDateTime.now()) &&
This line is used to make items that are from the past not display as the instructions suggest;
however, the sample JSON is all in the past, so this is commented out in order to show that data.
 */
                    interval.isBefore(it.mapToDateRange().endDate.minusMinutes(b.toLong()))
                ){
                    dateStrings.add(formatDateString(interval))
                    interval = interval.plusMinutes(c.toLong())
                }
            }
            _input.postValue(dateStrings)
        }
    }

    private fun formatDateString(ltd: LocalDateTime) : String {
        val timeZone = "Pacific Standard Time"
        var hour = ltd.hour
        var meridiem = "AM"
        if(hour == 0 ) hour+= 12 else if (hour > 12) { hour-=12; meridiem = "PM" }

        return "${ltd.dayOfWeek}, ${ltd.month} ${ltd.dayOfMonth}, ${ltd.year} at $hour:" +
                "${if(ltd.minute < 10)"0" else ""}${ltd.minute}:" +
                "${if(ltd.second < 10)"0" else ""}${ltd.second} $meridiem $timeZone"
    }
}