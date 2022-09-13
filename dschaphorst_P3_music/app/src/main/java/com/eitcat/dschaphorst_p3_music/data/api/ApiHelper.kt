package com.eitcat.dschaphorst_p3_music.data.api

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p3_music.data.model.MusicNetworkData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHelper {

    @GET(SEARCH_CLASSIC)
    suspend fun getClassicMusic(): LiveData<List<MusicNetworkData>>

    @GET(SEARCH_POP)
    suspend fun getPopMusic(): LiveData<List<MusicNetworkData>>

    @GET(SEARCH_ROCK)
    suspend fun getRockMusic(): LiveData<List<MusicNetworkData>>

    @GET("{song}")
    fun searchMusic(
        @Path("trackName") trackName: String
    ): LiveData<MusicNetworkData>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val SEARCH_CLASSIC ="${BASE_URL}search?term=classic&amp;media=music&amp;entity=song&amp;limit=50"
        const val SEARCH_POP = "${BASE_URL}search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
        const val SEARCH_ROCK = "${BASE_URL}search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
    }
}