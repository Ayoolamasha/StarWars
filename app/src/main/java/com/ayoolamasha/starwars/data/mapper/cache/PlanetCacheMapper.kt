package com.ayoolamasha.starwars.data.mapper.cache

import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.domain.model.Planets

fun PlanetEntity.toPlanetUICase(): Planets {
    return Planets(
        name = planetName,
        population = planetPopulation,
    )
}

fun Planets.toPlanetEntity(characterName: String): PlanetEntity {
    return PlanetEntity(
        planetName = name,
        planetPopulation = population,
        characterName = characterName
    )
}