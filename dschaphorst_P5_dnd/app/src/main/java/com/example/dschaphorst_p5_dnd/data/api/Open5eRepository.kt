package com.example.dschaphorst_p5_dnd.data.api

import com.example.dschaphorst_p5_dnd.data.model.SpellsNetworkData
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell
import com.example.dschaphorst_p5_dnd.data.model.domain.mapToSpellList
import com.example.dschaphorst_p5_dnd.util.FailureResponseFromServer
import com.example.dschaphorst_p5_dnd.util.NullResponseFromServer
import javax.inject.Inject
import javax.inject.Named

interface Open5eRepository {
    suspend fun getSpells(page: Int? = null): SpellsNetworkData
    var spellsData: List<Spell>
}

class Open5eRepositoryImpl @Inject constructor(
    @Named("open5eService") private val open5eServiceApi: Open5eServiceApi
) : Open5eRepository {
    override var spellsData: List<Spell> = emptyList()

    override suspend fun getSpells(page: Int?): SpellsNetworkData {
        val response = page?.let {
            open5eServiceApi.getSpells(page = it)
        } ?: open5eServiceApi.getSpells()
        if (response.isSuccessful) {
            response.body()?.let {
                spellsData = it.results.mapToSpellList()
                return it
            }
                ?: throw NullResponseFromServer("Spells are null, verify response from API.")
        } else {
            throw FailureResponseFromServer(response.errorBody()?.string())
        }
    }
}