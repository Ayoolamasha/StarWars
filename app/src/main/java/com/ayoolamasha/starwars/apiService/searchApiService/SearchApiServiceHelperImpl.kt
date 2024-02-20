package com.ayoolamasha.starwars.apiService.searchApiService

import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class SearchApiServiceHelperImpl(
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
}