package com.ayoolamasha.starwars.featureSearch.domain.model

data class CharacterUICase(
    val name: String?,
    val birthYear: String?,
    val height: String?,
    val films: List<String>?,
    val homeWorld: String?,
    val species: List<String>?,
    val url: String?,
)

data class CharacterDetails(
    val name: String?,
    val birthYear: String?,
    val height: String?,
    val films: List<String>?,
    val homeWorld: String?,
    val species: List<String>?,
    val url: String?,
)

data class FilmsUiCase(
    val title: String?,
    val openingCrawl: String?,
)

data class FilmsDetails(
    val title: String?,
    val openingCrawl: String?,
)


data class SpeciesUiCase(
    val name: String?,
    val language: String?,
    val homeWorld: String?,
)

data class PlanetUiCase(
    val name: String?,
    val population: String?,
)


data class CharacterDetailsUICase(
    val characterUICase: CharacterUICase?,
    val speciesUiCase: SpeciesUiCase?,
    val planetUiCase: PlanetUiCase?,
)

data class CharacterDetailsQueryParams(
    val url: String,
    val characterName: String
)

data class CharacterFilmsQueryParams(
    val url: List<String>?,
    val characterName: String
)
