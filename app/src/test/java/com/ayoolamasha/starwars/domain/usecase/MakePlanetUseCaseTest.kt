package com.ayoolamasha.starwars.domain.usecase

import com.ayoolamasha.starwars.domain.calls_usecase.MakePlanetCallsUseCase
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.repository.planetRepository.PlanetRepository
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakePlanets
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MakePlanetUseCaseTest {
    private val planetRepository: PlanetRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val planetUseCase = MakePlanetCallsUseCase(planetRepository = planetRepository, ioDispatcher =  ioDispatcher)


    @Test
    fun  `test that makePlanetCall returns success result`() = runBlocking {
        val characterPlanetData = fakePlanets

        coEvery { planetRepository.getPlanet(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Success(characterPlanetData))

        val result = planetUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Planets>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makePlanetCall returns error result`() = runBlocking {

        coEvery { planetRepository.getPlanet(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(
            NetworkResult.Error("Test error"))


        val result = planetUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<Planets>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}