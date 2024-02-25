package com.ayoolamasha.starwars.apiService.searchApiService

import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchApiService {

    @GET("people/")
    suspend fun searchCharacters(@Query("search") searchParam: String): Response<CharacterSearchResponse>

    @GET
    suspend fun getCharacterDetail(@Url characterUrl: String): Response<CharacterModelResponse>

    @GET
    suspend fun getFilmDetails(@Url filmsUrl: String): Response<FilmsResponse>

    @GET
    suspend fun getSpecieDetails(@Url speciesUrl: String): Response<SpeciesResponse>

    @GET
    suspend fun getPlanet(@Url planetUrl: String): Response<PlanetResponse>
}