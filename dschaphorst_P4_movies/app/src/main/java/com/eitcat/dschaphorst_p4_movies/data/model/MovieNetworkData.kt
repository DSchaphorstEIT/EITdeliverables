package com.eitcat.dschaphorst_p4_movies.data.model


import com.google.gson.annotations.SerializedName

data class MovieNetworkData(
    @SerializedName("dates")
    val dates: MovieDates? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Movie?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)