package com.example.dschaphorst_p5_dnd.data.api

import com.example.dschaphorst_p5_dnd.data.model.SpellsNetworkData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

interface Open5eRepository {
    suspend fun getSpells(): Response<SpellsNetworkData>
}

class Open5eRepositoryImpl @Inject constructor(
    @Named("open5eService") private val open5eServiceApi: Open5eServiceApi
): Open5eRepository {

    override suspend fun getSpells(): Response<SpellsNetworkData> = open5eServiceApi.getSpells()
}