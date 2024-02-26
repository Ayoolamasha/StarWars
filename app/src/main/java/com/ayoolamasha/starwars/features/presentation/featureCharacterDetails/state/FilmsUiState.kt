package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state

import com.ayoolamasha.starwars.domain.model.Films

data class FilmsUiState(
    val isLoading: Boolean = false,
    val films: List<Films>? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)