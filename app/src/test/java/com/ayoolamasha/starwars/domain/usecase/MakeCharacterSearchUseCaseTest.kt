package com.ayoolamasha.starwars.domain.usecase


import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterSearchCallsUseCase
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.repository.characterSearchRepository.CharacterSearchRepository
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterModelResponse
import com.ayoolamasha.starwars.utils.fakeCharacters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MakeCharacterSearchUseCaseTest {
    private val characterSearchRepository: CharacterSearchRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val characterSearchUseCase = MakeCharacterSearchCallsUseCase(characterSearchRepository = characterSearchRepository, ioDispatcher =  ioDispatcher)





    @Test
    fun  `test that makeCharacterSearchCall returns success result`() = runBlocking {
        val characterSearchData = listOf(fakeCharacters)

        coEvery { characterSearchRepository.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns flowOf(NetworkResult.Success(characterSearchData))

        val result = characterSearchUseCase.execute(params = "Luke SkyWalker")

        val resultList = mutableListOf<NetworkResult<List<Characters>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeCharacterSearchCall returns error result`() = runBlocking {

        coEvery { characterSearchRepository.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns flowOf(NetworkResult.Error("Test error"))


        val result = characterSearchUseCase.execute(params = "Luke SkyWalker")

        val resultList = mutableListOf<NetworkResult<List<Characters>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }








}