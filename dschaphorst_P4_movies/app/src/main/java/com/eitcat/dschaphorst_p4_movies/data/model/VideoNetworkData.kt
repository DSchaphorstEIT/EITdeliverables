package com.eitcat.dschaphorst_p4_movies.data.model


import com.google.gson.annotations.SerializedName

data class VideoNetworkData(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<Video?>? = null
)