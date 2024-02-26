package com.ayoolamasha.starwars.domain.usecase

import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterCallsUseCase
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.repository.characterRepository.CharacterRepository
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MakeCharacterUseCaseTest {
    private val characterRepository: CharacterRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val characterUseCase = MakeCharacterCallsUseCase(characterRepository = characterRepository, ioDispatcher =  ioDispatcher)


    @Test
    fun  `test that makeCharacterCall returns success result`() = runBlocking {
        val characterData = fakeCharacters

        coEvery { characterRepository.getCharacterDetail(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Success(characterData))

        val result = characterUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Characters>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeCharacterCall returns error result`() = runBlocking {

        coEvery { characterRepository.getCharacterDetail(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Error("Test error"))


        val result = characterUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Characters>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}