package com.example.dschaphorst_modiscc_calendar.util

import android.content.Context
import android.util.Log
import java.nio.charset.Charset

/**
 * Supporting Utility object to handle file input contained in the App Assets.
 */
object FileHandler {
    private const val TAG = "FileHandler"
    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString = try {
            val inputStream = context.assets.open(fileName ?: "calendars.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.defaultCharset())
        } catch (e: Exception) {
            Log.d(TAG, "getJsonFromAssets: ${e.localizedMessage}")
            return null
        }
        return jsonString
    }
}