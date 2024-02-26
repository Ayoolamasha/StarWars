package com.ayoolamasha.starwars.domain.calls_usecase

import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.repository.characterSearchRepository.CharacterSearchRepository
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeCharacterSearchCallsUseCase @Inject constructor(
    private val characterSearchRepository: CharacterSearchRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<String, NetworkResult<List<Characters>>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: String?): Flow<NetworkResult<List<Characters>>> {
        requireParams(params)
        return characterSearchRepository.searchCharacters(searchParam = params)
    }
}









