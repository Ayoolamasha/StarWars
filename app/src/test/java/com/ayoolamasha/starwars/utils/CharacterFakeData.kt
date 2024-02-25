package com.ayoolamasha.starwars.utils


import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase

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


val fakeCharacterUICase = CharacterUICase(
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

val fakePlanetUiCase = PlanetUiCase(
    name = "Earth",
    population = "7000000000",
)

val fakeSpeciesUICase = SpeciesUiCase(
    name = "Human",
    language = "English",
    homeWorld = "Earth",
)

val fakeFilmsUICase = FilmsUiCase(
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