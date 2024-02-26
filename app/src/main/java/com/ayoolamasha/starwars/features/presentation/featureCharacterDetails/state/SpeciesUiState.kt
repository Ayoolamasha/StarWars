package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state

import com.ayoolamasha.starwars.domain.model.Species

data class SpeciesUiState(
    val isLoading: Boolean = false,
    val species: Species? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)