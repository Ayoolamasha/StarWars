package com.ayoolamasha.starwars.features.presentation.featureSearch.model

data class CharacterDetails(
    val name: String?,
    val birthYear: String?,
    val height: String?,
    val films: List<String>?,
    val homeWorld: String?,
    val species: List<String>?,
    val url: String?,
)