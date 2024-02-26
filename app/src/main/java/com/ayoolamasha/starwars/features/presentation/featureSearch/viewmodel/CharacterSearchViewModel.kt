package com.ayoolamasha.starwars.features.presentation.featureSearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterSearchCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureSearch.state.CharacterSearchUiState
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val makeCharacterSearchCallsUseCase: MakeCharacterSearchCallsUseCase,

): ViewModel(){

    private val _characterSearchUiState = MutableStateFlow(CharacterSearchUiState())
    val characterSearchUiState: StateFlow<CharacterSearchUiState> = _characterSearchUiState


    fun makeCharacterSearchCall(searchParams:String){
        makeCharacterSearchCallsUseCase(searchParams).onEach { response ->
            try {
                when(response){
                    is NetworkResult.Loading ->{
                        _characterSearchUiState.update {
                            CharacterSearchUiState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success ->{
                        _characterSearchUiState.update {
                            CharacterSearchUiState(characterSearchUICase = response.data, isSuccessResult = true)
                        }
                    }

                    is NetworkResult.Error ->{
                        _characterSearchUiState.update {
                            CharacterSearchUiState(error = response.message, isErrorResult = true)
                        }
                    }

                    else -> {}
                }
            }catch (t : Throwable){
                if (t !is CancellationException){
                    _characterSearchUiState.update {
                        CharacterSearchUiState(error = response.message, isErrorResult = true)
                    }
                }else{
                    throw t
                }
            }

        }.launchIn(viewModelScope)
    }


}