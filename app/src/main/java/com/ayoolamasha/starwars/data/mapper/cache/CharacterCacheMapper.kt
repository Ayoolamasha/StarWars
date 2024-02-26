package com.ayoolamasha.starwars.data.mapper.cache

import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharactersEntity
import com.ayoolamasha.starwars.domain.model.Characters

fun CharactersEntity.toCharacterUICase(): Characters {
    return Characters(
        name = characterName,
        birthYear = characterBirthYear,
        height = characterHeight,
        films = characterFilms,
        homeWorld = characterHomeWorld,
        species = characterSpecies,
        url = characterDetailsUrl
    )
}

fun Characters.toCharacterEntity(): CharactersEntity {
    return CharactersEntity(
        characterName = name.toString(),
        characterBirthYear = birthYear,
        characterHeight = height,
        characterFilms = films,
        characterHomeWorld = homeWorld,
        characterSpecies = species,
        characterDetailsUrl = url
    )
}







