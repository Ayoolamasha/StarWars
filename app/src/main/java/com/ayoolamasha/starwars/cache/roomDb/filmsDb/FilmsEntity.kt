package com.ayoolamasha.starwars.cache.roomDb.filmsDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsEntity(
    val filmTitle: String?,
    val filmOpeningCrawl: String?,
    @PrimaryKey
    val characterName: String,
)
