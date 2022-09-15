package com.eitcat.dschaphorst_p3_music.data.model


import com.google.gson.annotations.SerializedName

data class MusicNetworkData(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val songData: List<SongData?>? = null
)