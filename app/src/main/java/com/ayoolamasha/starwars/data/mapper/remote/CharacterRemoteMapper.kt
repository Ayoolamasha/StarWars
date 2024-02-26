package com.ayoolamasha.starwars.data.mapper.remote

import com.ayoolamasha.starwars.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.domain.model.Characters

fun CharacterModelResponse.toCharacterUICase(): Characters {
    return Characters(
        name = name,
        birthYear = birthYear,
        height = height,
        films = films,
        homeWorld = homeWorld,
        species = species,
        url = url
    )
}

fun CharacterSearchResponse.toCharacterSearchUiCase(): List<Characters>{
    return searchResult?.map { it.toCharacterUICase() } ?: emptyList()
}







