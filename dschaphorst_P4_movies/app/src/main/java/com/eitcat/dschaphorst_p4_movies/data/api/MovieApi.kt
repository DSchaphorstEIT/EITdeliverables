package com.eitcat.dschaphorst_p4_movies.data.api

import com.eitcat.dschaphorst_p4_movies.data.model.MovieNetworkData
import com.eitcat.dschaphorst_p4_movies.data.model.VideoNetworkData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface used to control the possible Api calls. Holds the [serviceApi] object that
 * will be called from any fragment that needs to make an Api call.
 *
 */
interface MovieApi {

    @GET("{queryType}")
    suspend fun getMovie(
        @Path("queryType") queryType: String = PATH_DEFAULT,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<MovieNetworkData>

    @GET("{movieID}/video")
    suspend fun getVideo(
        @Path("movieID") movieID: Int = 0,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<VideoNetworkData>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        private const val PATH_DEFAULT = "popular"
        private const val LANGUAGE = "en-US"
        private const val PAGES = "1"

        // Note, hardcoding an Api key is EXTREMELY bad practice and should not be done in the future.
        private const val API_KEY = "819950d4cf35be1fb70d8746bc0796bf"

        val serviceApi: MovieApi = ApiService.movieService
    }
}