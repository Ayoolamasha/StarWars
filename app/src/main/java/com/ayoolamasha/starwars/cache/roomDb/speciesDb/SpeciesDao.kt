package com.ayoolamasha.starwars.cache.roomDb.speciesDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecies(speciesEntity: SpeciesEntity)

    @Query("DELETE FROM SpeciesEntity")
    suspend fun deleteAllSpecies()

    @Query("SELECT * FROM SpeciesEntity WHERE characterName = :characterName")
    fun getAllSpeciesByCharacter(characterName: String): SpeciesEntity
}