package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state

import com.ayoolamasha.starwars.domain.model.Planets

data class PlanetUiState(
    val isLoading: Boolean = false,
    val planets: Planets? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)