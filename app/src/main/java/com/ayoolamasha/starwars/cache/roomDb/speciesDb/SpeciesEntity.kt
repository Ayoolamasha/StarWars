package com.ayoolamasha.starwars.cache.roomDb.speciesDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpeciesEntity(
    val speciesName: String?,
    val speciesLanguage: String?,
    val speciesHomeWorld: String?,
    @PrimaryKey
    val characterName: String,
)
