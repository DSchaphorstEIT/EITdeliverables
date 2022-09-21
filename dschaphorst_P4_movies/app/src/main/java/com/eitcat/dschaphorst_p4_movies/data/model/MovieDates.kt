package com.eitcat.dschaphorst_p4_movies.data.model


import com.google.gson.annotations.SerializedName

data class MovieDates(
    @SerializedName("maximum")
    val maximum: String? = null,
    @SerializedName("minimum")
    val minimum: String? = null
)