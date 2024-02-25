package com.ayoolamasha.starwars.cache.roomDb.planetDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanet(planetEntity: PlanetEntity)

    @Query("DELETE FROM PlanetEntity")
    suspend fun deleteAllPlanets()

    @Query("SELECT * FROM PlanetEntity WHERE characterName = :characterName")
    fun getAllPlanetsByCharacterName(characterName: String): PlanetEntity
}