package com.ayoolamasha.starwars.data.mapper.remote

import com.ayoolamasha.starwars.data.model.FilmsResponse
import com.ayoolamasha.starwars.domain.model.Films

fun FilmsResponse.toFilmsUICase(): List<Films>{
    return listOf(
        Films(
        title = title,
        openingCrawl = openingCrawl
    )
    )
}