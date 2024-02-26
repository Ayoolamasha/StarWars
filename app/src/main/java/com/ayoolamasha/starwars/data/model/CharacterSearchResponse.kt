package com.ayoolamasha.starwars.data.model

import com.squareup.moshi.Json

data class CharacterSearchResponse(
    @Json(name = "results")
    val searchResult: List<CharacterModelResponse>?

)