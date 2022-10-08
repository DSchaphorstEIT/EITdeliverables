package com.example.dschaphorst_p5_dnd.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dschaphorst_p5_dnd.data.model.domain.Character5e
import io.reactivex.Completable

@Dao
interface CharDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character5e)

    @Query("SELECT * from character")
    fun getCharacters(): LiveData<List<Character5e>>

    @Delete
    fun deleteCharacter(character: Character5e): Completable
}