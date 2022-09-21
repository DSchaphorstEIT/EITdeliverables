package com.eitcat.dschaphorst_p4_movies.util

import com.eitcat.dschaphorst_p4_movies.data.model.Movie


/**
 * Used to hold the current status of the UI and allows different functionality
 * based on the current state. Mostly used to show that the Api is loading, and then
 * hold the [List] of [Movie] when the Api finishes successfully.
 *
 */
sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val movies: List<Movie>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
