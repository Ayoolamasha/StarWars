package com.ayoolamasha.starwars

import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity

val fakeCharacterEntity = CharactersEntity(
    characterName = "Luke SkyWalker",
    characterBirthYear  = "19BBY",
    characterHeight = "172",
    characterFilms = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    characterHomeWorld = "https://swapi.dev/api/planets/1/",
    characterSpecies = listOf(""),
    characterDetailsUrl = "https://swapi.dev/api/people/1/",
)

val fakeSpecieEntity = SpeciesEntity(
    speciesName = "Human",
    speciesLanguage = "English",
    speciesHomeWorld = "Earth",
    characterName = "Luke SkyWalker"
)

val fakePlanetEntity = PlanetEntity(
    planetName = "Earth",
    planetPopulation = "700",
    characterName = "Luke SkyWalker"
)

val fakeFilmsEntity = FilmsEntity(
    filmTitle = "A New Hope",
    filmOpeningCrawl = "Hope is Near",
    characterName = "Luke SkyWalker"
)