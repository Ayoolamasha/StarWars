package com.ayoolamasha.starwars.data.mapper.remote

import com.ayoolamasha.starwars.data.model.SpeciesResponse
import com.ayoolamasha.starwars.domain.model.Species

fun SpeciesResponse.toSpeciesUICase(): Species {
    return Species(
        name = name,
        language = language,
        homeWorld = homeWorld
    )
}