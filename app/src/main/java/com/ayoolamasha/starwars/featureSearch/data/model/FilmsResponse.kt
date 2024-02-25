package com.ayoolamasha.starwars.featureSearch.data.model

import com.squareup.moshi.Json

data class FilmsResponse(
    @Json(name = "title")
    val title: String?,
    @Json(name = "opening_crawl")
    val openingCrawl: String?,
)