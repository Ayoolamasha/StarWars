package com.ayoolamasha.starwars.presentation.featureCharacterDetails


import com.ayoolamasha.starwars.domain.calls_usecase.MakeSpeciesCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.SpeciesViewModel
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacters
import com.ayoolamasha.starwars.utils.fakeSpecies
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

class SpeciesViewModelTest {
    private lateinit var speciesViewModel: SpeciesViewModel
    private lateinit var makeSpeciesCallsUseCase: MakeSpeciesCallsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        makeSpeciesCallsUseCase = mockk()
        speciesViewModel = SpeciesViewModel(makeSpeciesCallsUseCase =  makeSpeciesCallsUseCase)

    }


    @Test
    fun `test that species call is successful`() = runBlocking {
        coEvery { makeSpeciesCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Success(fakeSpecies))
        }

        speciesViewModel.makeSpeciesCall(speciesUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())

        val state = speciesViewModel.speciesUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isSuccessResult).isTrue()
        Truth.assertThat(fakeSpecies).isEqualTo(state.species)
    }


    @Test
    fun `test that species call failed`() = runBlocking {
        val errorMessage = "Please Check Your Internet"
        coEvery { makeSpeciesCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        speciesViewModel.makeSpeciesCall(speciesUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())

        val state = speciesViewModel.speciesUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isErrorResult).isTrue()
        Truth.assertThat(errorMessage).isEqualTo(state.error)
    }
}