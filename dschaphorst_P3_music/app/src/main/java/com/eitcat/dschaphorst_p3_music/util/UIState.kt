package com.eitcat.dschaphorst_p3_music.util

import com.eitcat.dschaphorst_p3_music.data.model.Song

/**
 * Used to hold the current status of the UI and allows different functionality
 * based on the current state. Mostly used to show that the Api is loading, and then
 * hold the [List] of [Song] when the Api finishes successfully.
 *
 */
sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val songs: List<Song>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
