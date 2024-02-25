package com.ayoolamasha.starwars.featureSearch.data.mapper

import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase

fun CharactersEntity.toCharacterUICase(): CharacterUICase {
    return CharacterUICase(
        name = characterName,
        birthYear = characterBirthYear,
        height = characterHeight,
        films = characterFilms,
        homeWorld = characterHomeWorld,
        species = characterSpecies,
        url = characterDetailsUrl
    )
}

fun CharacterUICase.toCharacterEntity(): CharactersEntity {
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

fun SpeciesEntity.toSpeciesUICase(): SpeciesUiCase {
    return SpeciesUiCase(
        name = speciesName,
        language = speciesLanguage,
        homeWorld = speciesHomeWorld,
    )
}

fun SpeciesUiCase.toSpeciesEntity(characterName: String): SpeciesEntity {
    return SpeciesEntity(
        speciesName = name,
        speciesLanguage = language,
        speciesHomeWorld = homeWorld,
        characterName = characterName
    )
}

fun PlanetEntity.toPlanetUICase(): PlanetUiCase {
    return PlanetUiCase(
        name = planetName,
        population = planetPopulation,
    )
}

fun PlanetUiCase.toPlanetEntity(characterName: String): PlanetEntity {
    return PlanetEntity(
        planetName = name,
        planetPopulation = population,
        characterName = characterName
    )
}

fun FilmsEntity.toFilmsUICase(): FilmsUiCase {
    return FilmsUiCase(
        title = filmTitle,
        openingCrawl = filmOpeningCrawl,
    )
}

fun List<FilmsUiCase>.toFilmsListEntity(characterName: String): List<FilmsEntity> {
    return this.map {
        toFilmsEntity(
            title = it.title,
            openingCrawl = it.openingCrawl,
            characterName= characterName
        )
    }
}


fun toFilmsEntity(title: String?, openingCrawl: String?, characterName: String): FilmsEntity {
    return FilmsEntity(
        filmTitle = title,
        filmOpeningCrawl = openingCrawl,
        characterName = characterName
    )
}

