package com.example.dschaphorst_p5_dnd.data.api

import com.example.dschaphorst_p5_dnd.data.model.SpellsNetworkData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Open5eServiceApi {

    @GET(PATH_SPELLS)
    suspend fun getSpells(
        @Query("page") page: String = DEFAULT_PAGE,
        @Query("ordering") order: String = DEFAULT_ORDER,
        @Query("document__slug") source: String = DEFAULT_SOURCE
    ): Response<SpellsNetworkData>

    companion object{
        const val BASE_URL = "https://api.open5e.com/"

        private const val PATH_SPELLS = "spells"
        private const val DEFAULT_PAGE = "1"
        private const val DEFAULT_ORDER = "level_int"
        private const val DEFAULT_SOURCE = "wotc-srd"
    }
}