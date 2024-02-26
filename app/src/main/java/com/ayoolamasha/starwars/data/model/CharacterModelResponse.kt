package com.ayoolamasha.starwars.data.model

import com.squareup.moshi.Json

data class CharacterModelResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "birth_year")
    val birthYear: String?,
    @Json(name = "height")
    val height: String?,
    @Json(name = "films")
    val films: List<String>,
    @Json(name = "homeworld")
    val homeWorld: String?,
    @Json(name = "species")
    val species: List<String>?,
    @Json(name = "url")
    val url: String?,
)