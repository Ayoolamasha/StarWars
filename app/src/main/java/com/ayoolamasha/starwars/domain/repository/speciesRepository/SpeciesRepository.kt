package com.ayoolamasha.starwars.domain.repository.speciesRepository

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SpeciesRepository {

    fun getSpecieDetails(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<Species>>

    suspend fun deleteAllSpecies()
}