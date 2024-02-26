package com.ayoolamasha.starwars.apiService.searchApiService

import com.ayoolamasha.starwars.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.data.model.FilmsResponse
import com.ayoolamasha.starwars.data.model.PlanetResponse
import com.ayoolamasha.starwars.data.model.SpeciesResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchApiServiceHelperImpl @Inject constructor(
    private val searchApiService: SearchApiService
) : SearchApiServiceHelper {

    override suspend fun searchCharacters(searchParam: String): Response<CharacterSearchResponse> {
        return searchApiService.searchCharacters(searchParam = searchParam)
    }

    override suspend fun getCharacterDetail(characterUrl: String): Response<CharacterModelResponse> {
        return searchApiService.getCharacterDetail(characterUrl = characterUrl)
    }

    override suspend fun getFilmDetails(filmsUrl: String): Response<FilmsResponse> {
        return searchApiService.getFilmDetails(filmsUrl = filmsUrl)
    }

    override suspend fun getSpecieDetails(speciesUrl: String): Response<SpeciesResponse> {
        return searchApiService.getSpecieDetails(speciesUrl = speciesUrl)
    }

    override suspend fun getPlanet(planetUrl: String): Response<PlanetResponse> {
        return searchApiService.getPlanet(planetUrl = planetUrl)
    }
}