package com.ayoolamasha.starwars.domain.calls_usecase

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.domain.repository.speciesRepository.SpeciesRepository
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeSpeciesCallsUseCase @Inject constructor(
    private val speciesRepository: SpeciesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<Species>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<Species>> {
        requireParams(params)
        return speciesRepository.getSpecieDetails(characterDetailsQueryParams = params)
    }
}