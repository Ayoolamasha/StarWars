package com.ayoolamasha.starwars.utils


import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.data.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.data.model.FilmsResponse
import com.ayoolamasha.starwars.data.model.PlanetResponse
import com.ayoolamasha.starwars.data.model.SpeciesResponse
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.model.Species

val fakeCharacterModelResponse = CharacterModelResponse(
    name = "Luke SkyWalker",
    birthYear = "19BBY",
    height = "172",
    films = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    homeWorld = "https://swapi.dev/api/planets/1/",
    species = listOf(""),
    url = "https://swapi.dev/api/people/1/",
)

val fakeExpectedCharacterModelResponse = CharacterModelResponse(
    name = "Luke SkyWalker",
    birthYear = "19BBY",
    height = "172",
    films = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    homeWorld = "https://swapi.dev/api/planets/1/",
    species = listOf(""),
    url = "https://swapi.dev/api/people/1/",
)

val fakeCharacterSearchResponse = CharacterSearchResponse(
    searchResult = listOf(fakeCharacterModelResponse)
)

val fakePlanetResponse = PlanetResponse(
    name = "Earth",
    population = "7000000000",
)

val fakeSpeciesResponse = SpeciesResponse(
    name = "Human",
    language = "English",
    homeWorld = "Earth",
)

val fakeFilmsResponse = FilmsResponse(
    title = "A New Hope",
    openingCrawl = "Hope Is Near"
)


val fakeCharacters = Characters(
    name = "Luke SkyWalker",
    birthYear = "19BBY",
    height = "172",
    films = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    homeWorld = "https://swapi.dev/api/planets/1/",
    species = listOf(""),
    url = "https://swapi.dev/api/people/1/",
)

val fakePlanets = Planets(
    name = "Earth",
    population = "7000000000",
)

val fakeSpecies = Species(
    name = "Human",
    language = "English",
    homeWorld = "Earth",
)

val fakeFilms = Films(
    title = "A New Hope",
    openingCrawl = "Hope Is Near"
)

val fakeCharacterDetailsQueryParams = CharacterDetailsQueryParams(
    url = "https://swapi.dev/api/people/1/",
    characterName = "Luke SkyWalker"
)

val fakeCharacterFilmsQueryParams = CharacterFilmsQueryParams(
    url = listOf(
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/2/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/6/"
    ),
    characterName = "Luke SkyWalker"
)

