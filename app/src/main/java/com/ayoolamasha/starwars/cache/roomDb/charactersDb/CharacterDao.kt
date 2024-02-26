package com.ayoolamasha.starwars.cache.roomDb.charactersDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(charactersEntity: CharactersEntity)

    @Delete
    suspend fun deleteCharacter(charactersEntity: CharactersEntity)

    @Query("DELETE FROM CharactersEntity")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM CharactersEntity")
    fun getAllCharacters(): Flow<List<CharactersEntity>>

    @Query("SELECT * FROM CharactersEntity WHERE characterName = :characterName")
    fun getCharacterByName(characterName: String): CharactersEntity
}