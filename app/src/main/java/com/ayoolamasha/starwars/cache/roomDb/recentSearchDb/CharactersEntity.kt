package com.ayoolamasha.starwars.cache.roomDb.recentSearchDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharactersEntity(
    @PrimaryKey
    val characterName: String,
    val characterBirthYear: String?,
    val characterHeight: String?,
    val characterFilms: List<String>?,
    val characterHomeWorld: String?,
    val characterSpecies: List<String>?,
    val characterDetailsUrl: String?,
)

