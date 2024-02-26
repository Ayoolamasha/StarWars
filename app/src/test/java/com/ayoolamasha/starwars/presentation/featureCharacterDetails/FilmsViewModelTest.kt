package com.ayoolamasha.starwars.presentation.featureCharacterDetails


import com.ayoolamasha.starwars.domain.calls_usecase.MakeFilmsCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.FilmsViewModel
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterFilmsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacters
import com.ayoolamasha.starwars.utils.fakeFilms
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class FilmsViewModelTest {
    private lateinit var filmsViewModel: FilmsViewModel
    private lateinit var makeFilmsCallsUseCase: MakeFilmsCallsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        makeFilmsCallsUseCase = mockk()
        filmsViewModel = FilmsViewModel(makeFilmsCallsUseCase =  makeFilmsCallsUseCase)

    }


    @Test
    fun `test that search call is successful`() = runBlocking {
        coEvery { makeFilmsCallsUseCase(params = fakeCharacterFilmsQueryParams) } returns flow {
            emit(NetworkResult.Success(listOf(fakeFilms)))
        }

        filmsViewModel.makeFilmsCall(filmsUrl = fakeCharacters.films, characterName = fakeCharacters.name.toString())

        val state = filmsViewModel.filmsUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isSuccessResult).isTrue()
        Truth.assertThat(listOf(fakeFilms)).isEqualTo(state.films)
    }


    @Test
    fun `test that search call failed`() = runBlocking {
        val errorMessage = "Please Check Your Internet"
        coEvery { makeFilmsCallsUseCase(params = fakeCharacterFilmsQueryParams) } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        filmsViewModel.makeFilmsCall(filmsUrl = fakeCharacters.films, characterName = fakeCharacters.name.toString())

        val state = filmsViewModel.filmsUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isErrorResult).isTrue()
        Truth.assertThat(errorMessage).isEqualTo(state.error)
    }
}