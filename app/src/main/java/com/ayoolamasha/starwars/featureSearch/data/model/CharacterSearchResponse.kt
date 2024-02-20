package com.ayoolamasha.starwars.featureSearch.data.model

import com.squareup.moshi.Json

data class CharacterSearchResponse(
    @Json(name = "results")
    val searchResult: List<CharacterModelResponse>?

)