package com.ayoolamasha.starwars.apiService.searchApiService

import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import retrofit2.Response

interface SearchApiServiceHelper {
    suspend fun searchCharacters(searchParam: String): Response<CharacterSearchResponse>

    suspend fun getCharacterDetail(characterUrl: String): Response<CharacterModelResponse>

    suspend fun getFilmDetails(filmsUrl: String): Response<FilmsResponse>

    suspend fun getSpecieDetails(speciesUrl: String): Response<SpeciesResponse>

    suspend fun getPlanet(planetUrl: String): Response<PlanetResponse>
}