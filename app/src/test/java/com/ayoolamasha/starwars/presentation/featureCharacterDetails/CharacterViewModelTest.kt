package com.ayoolamasha.starwars.presentation.featureCharacterDetails

import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel.CharacterViewModel
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacters
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

class CharacterViewModelTest {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var makeCharacterCallsUseCase: MakeCharacterCallsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        makeCharacterCallsUseCase = mockk()
        characterViewModel = CharacterViewModel(makeCharacterCallsUseCase =  makeCharacterCallsUseCase)

    }


    @Test
    fun `test that character call is successful`() = runBlocking {
        coEvery { makeCharacterCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Success(fakeCharacters))
        }

        characterViewModel.makeCharacterCall(characterUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())

        val state = characterViewModel.characterUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isSuccessResult).isTrue()
        Truth.assertThat(fakeCharacters).isEqualTo(state.characters)
    }


    @Test
    fun `test that character call failed`() = runBlocking {
        val errorMessage = "Please Check Your Internet"
        coEvery { makeCharacterCallsUseCase(params = fakeCharacterDetailsQueryParams) } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        characterViewModel.makeCharacterCall(characterUrl = fakeCharacters.url.toString(), characterName = fakeCharacters.name.toString())


        val state = characterViewModel.characterUiState.value

        val job = launch {
            TestCase.assertTrue(state.isLoading)
        }

        job.cancel()

        Truth.assertThat(state.isLoading).isFalse()
        Truth.assertThat(state.isErrorResult).isTrue()
        Truth.assertThat(errorMessage).isEqualTo(state.error)
    }
}