package com.ayoolamasha.starwars.features.presentation.featureSearch.state

import com.ayoolamasha.starwars.domain.model.Characters

data class CharacterSearchUiState(
    val isLoading: Boolean = false,
    val characterSearchUICase: List<Characters>? = null,
    val isSuccessResult: Boolean = false,
    val error: String? = "",
    val isErrorResult: Boolean = false,
)