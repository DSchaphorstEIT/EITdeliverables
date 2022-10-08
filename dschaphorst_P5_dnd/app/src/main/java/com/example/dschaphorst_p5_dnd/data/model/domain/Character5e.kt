package com.example.dschaphorst_p5_dnd.data.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dschaphorst_p5_dnd.util.Classes5e

@Entity(tableName = "character")
data class Character5e(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String = "",
    val race: String = "",
    val dndClass: Classes5e = Classes5e.OTHER,
    val level: Int = 1,
    val spellsKnown: List<Spell> = emptyList(),
    val spellsPrepared: List<Spell> = emptyList()
)
