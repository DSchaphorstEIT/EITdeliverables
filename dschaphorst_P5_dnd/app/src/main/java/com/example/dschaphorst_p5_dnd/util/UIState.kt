package com.example.dschaphorst_p5_dnd.util

import com.example.dschaphorst_p5_dnd.data.model.SpellsData
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val spells: List<Spell>) : UIState()
    data class ERROR(val e: Exception) : UIState()
}
