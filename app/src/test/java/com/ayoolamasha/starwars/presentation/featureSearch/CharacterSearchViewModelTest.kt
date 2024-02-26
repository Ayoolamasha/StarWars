package com.ayoolamasha.starwars.presentation.featureSearch

import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterSearchCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureSearch.viewmodel.CharacterSearchViewModel
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacters
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class CharacterSearchViewModelTest {
    private lateinit var characterSearchViewModel: CharacterSearchViewModel
    private lateinit var makeCharacterSearchCallsUseCase: MakeCharacterSearchCallsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        makeCharacterSearchCallsUseCase = mockk()
        characterSearchViewModel = CharacterSearchViewModel(makeCharacterSearchCallsUseCase =  makeCharacterSearchCallsUseCase)

    }


    @Test
    fun `test that search call is successful`() = runBlocking {
        coEvery { makeCharacterSearchCallsUseCase(params = "Luke") } returns flow {
            emit(NetworkResult.Success(listOf(fakeCharacters)))
        }

        characterSearchViewModel.makeCharacterSearchCall(searchParams = "Luke")

        val state = characterSearchViewModel.characterSearchUiState.value

        val job = launch {
            assertTrue(state.isLoading)
        }

        job.cancel()

        assertThat(state.isLoading).isFalse()
        assertThat(state.isSuccessResult).isTrue()
        assertThat(listOf(fakeCharacters)).isEqualTo(state.characterSearchUICase)
    }


    @Test
    fun `test that search call failed`() = runBlocking {
        val errorMessage = "Please Check Your Internet"
        coEvery { makeCharacterSearchCallsUseCase(params = "Luke") } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        characterSearchViewModel.makeCharacterSearchCall(searchParams = "Luke")

        val state = characterSearchViewModel.characterSearchUiState.value

        val job = launch {
            assertTrue(state.isLoading)
        }

        job.cancel()

        assertThat(state.isLoading).isFalse()
        assertThat(state.isErrorResult).isTrue()
        assertThat(errorMessage).isEqualTo(state.error)
    }
}