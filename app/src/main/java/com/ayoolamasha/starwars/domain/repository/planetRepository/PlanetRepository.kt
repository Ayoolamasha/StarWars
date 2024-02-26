package com.ayoolamasha.starwars.domain.repository.planetRepository

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanet(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<Planets>>

    suspend fun deleteAllPlanets()
}