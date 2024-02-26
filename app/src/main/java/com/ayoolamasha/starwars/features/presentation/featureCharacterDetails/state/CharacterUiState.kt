package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state

import com.ayoolamasha.starwars.domain.model.Characters

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: Characters? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)