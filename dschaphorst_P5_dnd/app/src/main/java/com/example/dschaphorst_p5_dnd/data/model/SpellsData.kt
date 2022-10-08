package com.example.dschaphorst_p5_dnd.data.model


import com.google.gson.annotations.SerializedName

data class SpellsData(
    @SerializedName("archetype")
    val archetype: String?,
    @SerializedName("casting_time")
    val castingTime: String?,
    @SerializedName("circles")
    val circles: String?,
    @SerializedName("components")
    val components: String?,
    @SerializedName("concentration")
    val concentration: String?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("dnd_class")
    val dndClass: String?,
    @SerializedName("document__license_url")
    val documentLicenseUrl: String?,
    @SerializedName("document__slug")
    val documentSlug: String?,
    @SerializedName("document__title")
    val documentTitle: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("higher_level")
    val higherLevel: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("level_int")
    val levelInt: Int?,
    @SerializedName("material")
    val material: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("page")
    val page: String?,
    @SerializedName("range")
    val range: String?,
    @SerializedName("ritual")
    val ritual: String?,
    @SerializedName("school")
    val school: String?,
    @SerializedName("slug")
    val slug: String?
)