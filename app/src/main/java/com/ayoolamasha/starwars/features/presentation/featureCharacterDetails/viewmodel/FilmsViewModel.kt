package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.data.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.domain.calls_usecase.MakeFilmsCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.FilmsUiState
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
class FilmsViewModel @Inject constructor(
    private val makeFilmsCallsUseCase: MakeFilmsCallsUseCase,
): ViewModel(){


    private val _filmsUiState = MutableStateFlow(FilmsUiState())
    val filmsUiState: StateFlow<FilmsUiState> = _filmsUiState



    fun makeFilmsCall(filmsUrl: List<String>?, characterName: String){
        val request = CharacterFilmsQueryParams(url = filmsUrl, characterName = characterName)
        makeFilmsCallsUseCase(request).onEach { response ->
            try {
                when(response){
                    is NetworkResult.Loading ->{
                        _filmsUiState.update {
                            FilmsUiState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success ->{
                        _filmsUiState.update {
                            FilmsUiState(films = response.data, isSuccessResult = true)
                        }
                    }

                    is NetworkResult.Error ->{
                        _filmsUiState.update {
                            FilmsUiState(error = response.message, isErrorResult = true)
                        }
                    }

                    else -> {}
                }
            }catch (t : Throwable){
                if (t !is CancellationException){
                    _filmsUiState.update {
                        FilmsUiState(error = response.message, isErrorResult = true)
                    }
                }else{
                    throw t
                }
            }

        }.launchIn(viewModelScope)
    }



}