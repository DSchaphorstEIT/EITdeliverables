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

    /**
     * The basic movie data retrieval API caller.
     *
     * @param queryType The type of query being sent to the API.
     * @param key Access key to allow data pulls
     * @param language language to use from the API
     * @param page Number of pages of data to pull
     * @return [Response] from API converted to [MovieNetworkData] using GSON
     */
    @GET("{queryType}")
    suspend fun getMovie(
        @Path("queryType") queryType: String = PATH_DEFAULT,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: String = PAGES,
    ): Response<MovieNetworkData>

    /**
     * Once a movie is selected, use this API caller to get the associated videos.
     *
     * @param movieID The ID number of the Movie whose videos we are looking for.
     * @param key Access key to allow data pulls
     * @param language language to use from the API
     * @return [Response] from API converted to [VideoNetworkData] using GSON
     */
    @GET("{movieID}/videos")
    suspend fun getVideo(
        @Path("movieID") movieID: Int = 0,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE
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