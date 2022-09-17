package com.eitcat.dschaphorst_p3_music.data.model


import com.google.gson.annotations.SerializedName

/**
 * The initial data that is pulled from the iTunes Api: [com.eitcat.dschaphorst_p3_music.data.api.ApiHelper]
 * Created from the JSON to Kotlin Data Class plugin
 *
 * @property resultCount The count of items from the search result.
 * @property songData The [List] of [SongData] obtained that will be cast into [Song].
 */
data class MusicNetworkData(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val songData: List<SongData?>? = null
)