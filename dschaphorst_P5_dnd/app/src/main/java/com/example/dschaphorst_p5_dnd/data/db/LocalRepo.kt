package com.example.dschaphorst_p5_dnd.data.db

import androidx.lifecycle.LiveData
import com.example.dschaphorst_p5_dnd.data.model.domain.Character5e

class LocalRepo (
    private val charDAO: CharDAO
) {
    var characters : LiveData<List<Character5e>> = charDAO.getCharacters()

    fun insertCharacter (character5e: Character5e) {
        charDAO.insertCharacter(character5e)
    }
}