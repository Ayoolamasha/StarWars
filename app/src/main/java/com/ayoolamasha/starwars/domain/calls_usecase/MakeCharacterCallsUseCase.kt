package com.ayoolamasha.starwars.domain.calls_usecase

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.repository.characterRepository.CharacterRepository
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeCharacterCallsUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<Characters>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<Characters>> {
        requireParams(params)
        return characterRepository.getCharacterDetail(characterDetailsQueryParams = params)
    }
}