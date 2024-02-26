package com.ayoolamasha.starwars.domain.usecase

import com.ayoolamasha.starwars.domain.calls_usecase.MakeSpeciesCallsUseCase
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.domain.repository.speciesRepository.SpeciesRepository
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeSpecies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MakeSpeciesUseCaseTest {
    private val speciesRepository: SpeciesRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val speciesUseCase = MakeSpeciesCallsUseCase(speciesRepository = speciesRepository, ioDispatcher =  ioDispatcher)

    @Test
    fun  `test that makeSpeciesCall returns success result`() = runBlocking {
        val characterSpeciesData = fakeSpecies

        coEvery { speciesRepository.getSpecieDetails(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Success(characterSpeciesData))

        val result = speciesUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Species>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeSpeciesCall returns error result`() = runBlocking {

        coEvery { speciesRepository.getSpecieDetails(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Error("Test error"))


        val result = speciesUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Species>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}