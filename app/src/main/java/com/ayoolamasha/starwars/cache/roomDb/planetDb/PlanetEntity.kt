package com.ayoolamasha.starwars.cache.roomDb.planetDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    val planetName: String?,
    val planetPopulation: String?,
    @PrimaryKey
    val characterName: String,
)
