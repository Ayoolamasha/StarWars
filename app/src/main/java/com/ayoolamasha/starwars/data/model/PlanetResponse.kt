package com.ayoolamasha.starwars.data.model

import com.squareup.moshi.Json

data class PlanetResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "population")
    val population: String?,
)
