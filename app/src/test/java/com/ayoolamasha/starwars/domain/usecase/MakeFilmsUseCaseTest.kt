package com.ayoolamasha.starwars.domain.usecase

import com.ayoolamasha.starwars.domain.calls_usecase.MakeFilmsCallsUseCase
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.repository.filmsRepository.FilmsRepository
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterFilmsQueryParams
import com.ayoolamasha.starwars.utils.fakeFilms
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class MakeFilmsUseCaseTest {
    private val filmsRepository: FilmsRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val filmsUseCase = MakeFilmsCallsUseCase(filmsRepository = filmsRepository, ioDispatcher =  ioDispatcher)



    @Test
    fun  `test that makeFilmsCall returns success result`() = runBlocking {
        val characterFilmsData = listOf(fakeFilms)

        coEvery { filmsRepository.getFilmDetails(characterFilmsQueryParams = fakeCharacterFilmsQueryParams) } returns flowOf(
            NetworkResult.Success(characterFilmsData))

        val result = filmsUseCase.execute(params = fakeCharacterFilmsQueryParams)

        val resultList = mutableListOf<NetworkResult<List<Films>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeFilmsCall returns error result`() = runBlocking {

        coEvery { filmsRepository.getFilmDetails(characterFilmsQueryParams = fakeCharacterFilmsQueryParams) } returns flowOf(
            NetworkResult.Error("Test error"))


        val result = filmsUseCase.execute(params = fakeCharacterFilmsQueryParams)

        val resultList = mutableListOf<NetworkResult<List<Films>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}