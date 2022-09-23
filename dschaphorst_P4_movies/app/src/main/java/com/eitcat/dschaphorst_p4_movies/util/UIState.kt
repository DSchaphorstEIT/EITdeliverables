package com.eitcat.dschaphorst_p4_movies.util

import com.eitcat.dschaphorst_p4_movies.data.model.Movie
import com.eitcat.dschaphorst_p4_movies.data.model.Video


/**
 * Used to hold the current status of the UI and allows different functionality
 * based on the current state. Mostly used to show that the Api is loading, and then
 * hold the [List] of [Movie] or [Video] when the Api finishes successfully.
 *
 */
sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(
        var movies: List<Movie> = emptyList(),
        var videos: List<Video> = emptyList()
        ) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
