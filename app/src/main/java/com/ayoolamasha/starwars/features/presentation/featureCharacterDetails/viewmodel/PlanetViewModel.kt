package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.calls_usecase.MakePlanetCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.PlanetUiState
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
class PlanetViewModel @Inject constructor(
    private val makePlanetCallsUseCase: MakePlanetCallsUseCase,
): ViewModel(){



    private val _planetUiState = MutableStateFlow(PlanetUiState())
    val planetUiState: StateFlow<PlanetUiState> = _planetUiState



    fun makePlanetCall(planetUrl:String, characterName: String){
        val request = CharacterDetailsQueryParams(url = planetUrl, characterName = characterName)
        makePlanetCallsUseCase(request).onEach { response ->
            try {
                when(response){
                    is NetworkResult.Loading ->{
                        _planetUiState.update {
                            PlanetUiState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success ->{
                        _planetUiState.update {
                            PlanetUiState(planets = response.data, isSuccessResult = true)
                        }
                    }

                    is NetworkResult.Error ->{
                        _planetUiState.update {
                            PlanetUiState(error = response.message, isErrorResult = true)
                        }
                    }

                    else -> {}
                }
            }catch (t : Throwable){
                if (t !is CancellationException){
                    _planetUiState.update {
                        PlanetUiState(error = response.message, isErrorResult = true)
                    }
                }else{
                    throw t
                }
            }

        }.launchIn(viewModelScope)
    }




}