package com.eitcat.dschaphorst_p3_music.util

import com.eitcat.dschaphorst_p3_music.data.model.Song

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val songs: List<Song>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
