package com.ayoolamasha.starwars.data.model

data class CharacterDetailsQueryParams(
    val url: String,
    val characterName: String
)

data class CharacterFilmsQueryParams(
    val url: List<String>?,
    val characterName: String
)