package com.ayoolamasha.starwars.presentation.featureCharacterDetails


import com.ayoolamasha.starwars.domain.calls_usecase.MakePlanetCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.PlanetViewModel
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacters
import com.ayoolamasha.starwars.utils.fakePlanets
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

class PlanetViewModelTest {
    private lateinit var planetViewModel: PlanetViewModel
    private lateinit var makePlanetCallsUseCase: MakePlanetCallsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        makePlanetCallsUseCase = mockk()
        planetViewModel = PlanetViewModel(makePlanetCallsUseCase =  makePlanetCallsUseCase)

    }


    @Test
    fun `test that planet call is successful`() = runBlocking {
        coEvery { makePlanetCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Success(fakePlanets))
        }

        planetViewModel.makePlanetCall(planetUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())

        val state = planetViewModel.planetUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isSuccessResult).isTrue()
        Truth.assertThat(fakePlanets).isEqualTo(state.planets)
    }


    @Test
    fun `test that search call failed`() = runBlocking {
        val errorMessage = "Please Check Your Internet"
        coEvery { makePlanetCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        planetViewModel.makePlanetCall(planetUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())

        val state = planetViewModel.planetUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isErrorResult).isTrue()
        Truth.assertThat(errorMessage).isEqualTo(state.error)
    }
}