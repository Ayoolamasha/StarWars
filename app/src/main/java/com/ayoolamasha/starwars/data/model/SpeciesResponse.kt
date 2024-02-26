package com.ayoolamasha.starwars.data.model

import com.squareup.moshi.Json

data class SpeciesResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "homeworld")
    val homeWorld: String?,
)
