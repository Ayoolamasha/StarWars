package com.ayoolamasha.starwars.domain.calls_usecase

import com.ayoolamasha.starwars.data.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.repository.filmsRepository.FilmsRepository
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeFilmsCallsUseCase @Inject constructor(
    private val filmsRepository: FilmsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterFilmsQueryParams, NetworkResult<List<Films>>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterFilmsQueryParams?): Flow<NetworkResult<List<Films>>> {
        requireParams(params)
        return filmsRepository.getFilmDetails(characterFilmsQueryParams = params)
    }
}