package com.ayoolamasha.starwars.featureSearch.presentation.state

import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase

data class CharacterSearchUiState(
    val isLoading: Boolean = false,
    val characterSearchUICase: List<CharacterUICase>? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characterUICase: CharacterUICase? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)

data class PlanetUiState(
    val isLoading: Boolean = false,
    val planetUiCase: PlanetUiCase? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)

data class SpeciesUiState(
    val isLoading: Boolean = false,
    val speciesUiCase: SpeciesUiCase? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)

data class FilmsUiState(
    val isLoading: Boolean = false,
    val filmsUiCase: List<FilmsUiCase>? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)