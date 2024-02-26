package com.ayoolamasha.starwars.domain.calls_usecase

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.repository.planetRepository.PlanetRepository
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakePlanetCallsUseCase @Inject constructor(
    private val planetRepository: PlanetRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<Planets>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<Planets>> {
        requireParams(params)
        return planetRepository.getPlanet(characterDetailsQueryParams = params)
    }
}