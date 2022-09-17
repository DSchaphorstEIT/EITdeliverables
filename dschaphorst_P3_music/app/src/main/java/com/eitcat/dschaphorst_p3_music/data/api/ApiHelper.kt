package com.eitcat.dschaphorst_p3_music.data.api

import com.eitcat.dschaphorst_p3_music.data.model.MusicNetworkData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used to control the possible Api calls. Holds the [serviceApi] object that
 * will be called from any fragment that needs to make an Api call.
 *
 */
interface ApiHelper {

    @GET(PATH_SEARCH)
    suspend fun getMusic(
        @Query("term") term: String = "techno",
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50,
    ): Response<MusicNetworkData>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        private const val PATH_SEARCH = "search"

        val serviceApi: ApiHelper = ApiService.musicService
    }
}