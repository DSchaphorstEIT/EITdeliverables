package com.example.dschaphorst_p5_dnd.data.model.domain

import android.util.Log
import com.example.dschaphorst_p5_dnd.data.model.SpellsData
import com.example.dschaphorst_p5_dnd.util.Classes5e
import java.util.*

private const val TAG = "Spell"

data class Spell(
    val name: String,
    val lvl: Int,
    val level: String,
    val school: String,
    val ritual: Boolean,
    val castingTime: String,
    val range: String,
    val components: String,
    val material: String,
    val duration: String,
    val concentration: Boolean,
    val dndClass: String,
    val desc: String,
    val higherLevel: String
)

fun List<SpellsData?>?.mapToSpellList(): List<Spell> =
    this?.map { it ->
        Spell(
            name = it?.name ?: "",
            lvl = it?.levelInt ?: -1,
            level = it?.level?.split("-")?.first() ?: "",
            school = it?.school ?: "",
            ritual = it?.ritual == "yes",
            castingTime = it?.castingTime ?: "",
            range = it?.range ?: "",
            components = it?.components ?: "",
            material = it?.material ?: "",
            duration = it?.duration ?: "",
            concentration = it?.concentration == "yes",
            dndClass = it?.dndClass ?: "",
            desc = it?.desc ?: "",
            higherLevel = it?.higherLevel ?: ""
        )
    } ?: emptyList()