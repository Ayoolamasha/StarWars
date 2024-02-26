package com.ayoolamasha.starwars.data.mapper.remote

import com.ayoolamasha.starwars.data.model.PlanetResponse
import com.ayoolamasha.starwars.domain.model.Planets

fun PlanetResponse.toPlanetUICase(): Planets {
    return Planets(
        name = name,
        population = population
    )
}