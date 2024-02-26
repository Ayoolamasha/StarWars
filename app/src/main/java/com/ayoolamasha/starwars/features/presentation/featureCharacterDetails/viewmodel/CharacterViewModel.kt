package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.calls_usecase.MakeCharacterCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.CharacterUiState
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
class CharacterViewModel @Inject constructor(
    private val makeCharacterCallsUseCase: MakeCharacterCallsUseCase,
): ViewModel(){


    private val _characterUiState = MutableStateFlow(CharacterUiState())
    val characterUiState: StateFlow<CharacterUiState> = _characterUiState





    fun makeCharacterCall(characterUrl:String, characterName: String){
        val request = CharacterDetailsQueryParams(url = characterUrl, characterName = characterName)
        makeCharacterCallsUseCase(request).onEach { response ->
            try {
                when(response){
                    is NetworkResult.Loading ->{
                        _characterUiState.update {
                            CharacterUiState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success ->{
                        _characterUiState.update {
                            CharacterUiState(characters = response.data, isSuccessResult = true)
                        }
                    }

                    is NetworkResult.Error ->{
                        _characterUiState.update {
                            CharacterUiState(error = response.message, isErrorResult = true)
                        }
                    }

                    else -> {}
                }
            }catch (t : Throwable){
                if (t !is CancellationException){
                    _characterUiState.update {
                        CharacterUiState(error = response.message, isErrorResult = true)
                    }
                }else{
                    throw t
                }
            }

        }.launchIn(viewModelScope)
    }



}