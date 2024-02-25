package com.ayoolamasha.starwars.featureSearch.data.mapper

import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase


fun CharacterModelResponse.toCharacterUICase(): CharacterUICase{
    return CharacterUICase(
        name = name,
        birthYear = birthYear,
        height = height,
        films = films,
        homeWorld = homeWorld,
        species = species,
        url = url
    )
}

fun CharacterSearchResponse.toCharacterSearchUiCase(): List<CharacterUICase>{
    return searchResult?.map { it.toCharacterUICase() } ?: emptyList()
}


fun FilmsResponse.toFilmsUICase(): List<FilmsUiCase>{
    return listOf(FilmsUiCase(
        title = title,
        openingCrawl = openingCrawl
    ))
}

fun PlanetResponse.toPlanetUICase(): PlanetUiCase{
    return PlanetUiCase(
        name = name,
        population = population
    )
}

fun SpeciesResponse.toSpeciesUICase(): SpeciesUiCase{
    return SpeciesUiCase(
        name = name,
        language = language,
        homeWorld = homeWorld
    )
}
