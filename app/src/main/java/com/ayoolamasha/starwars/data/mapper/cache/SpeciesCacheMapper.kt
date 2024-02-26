package com.ayoolamasha.starwars.data.mapper.cache

import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.domain.model.Species

fun SpeciesEntity.toSpeciesUICase(): Species {
    return Species(
        name = speciesName,
        language = speciesLanguage,
        homeWorld = speciesHomeWorld,
    )
}

fun Species.toSpeciesEntity(characterName: String): SpeciesEntity {
    return SpeciesEntity(
        speciesName = name,
        speciesLanguage = language,
        speciesHomeWorld = homeWorld,
        characterName = characterName
    )
}