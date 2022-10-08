package com.example.dschaphorst_p5_dnd.data.model


import com.google.gson.annotations.SerializedName

data class SpellsNetworkData(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<SpellsData?>?
)