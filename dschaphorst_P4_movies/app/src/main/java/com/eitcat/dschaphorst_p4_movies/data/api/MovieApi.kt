package com.eitcat.dschaphorst_p4_movies.data.api

import com.eitcat.dschaphorst_p4_movies.data.model.MovieNetworkData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used to control the possible Api calls. Holds the [serviceApi] object that
 * will be called from any fragment that needs to make an Api call.
 *
 */
interface MovieApi {

    @GET(PATH_POP)
    suspend fun getMoviePop(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<MovieNetworkData>

    @GET(PATH_NOW)
    suspend fun getMovieNow(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<MovieNetworkData>

    @GET(PATH_SOON)
    suspend fun getMovieSoon(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<MovieNetworkData>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        private const val PATH_POP = "popular"
        private const val PATH_NOW = "now_playing"
        private const val PATH_SOON = "upcoming"
        private const val LANGUAGE = "en-US"
        private const val PAGES = "1"

        // Note, hardcoding an Api key is EXTREMELY bad practice and should not be done in the future.
        private const val API_KEY = "819950d4cf35be1fb70d8746bc0796bf"

        val serviceApi: MovieApi = ApiService.movieService
    }
}