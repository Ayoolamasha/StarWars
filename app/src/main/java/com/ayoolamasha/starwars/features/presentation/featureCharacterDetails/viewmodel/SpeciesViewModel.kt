package com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.calls_usecase.MakeSpeciesCallsUseCase
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.state.SpeciesUiState
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
class SpeciesViewModel @Inject constructor(
    private val makeSpeciesCallsUseCase: MakeSpeciesCallsUseCase,
): ViewModel(){

    private val _speciesUiState = MutableStateFlow(SpeciesUiState())
    val speciesUiState: StateFlow<SpeciesUiState> = _speciesUiState



    fun makeSpeciesCall(speciesUrl:String, characterName: String){
        val request = CharacterDetailsQueryParams(url = speciesUrl, characterName = characterName)
        makeSpeciesCallsUseCase(request).onEach { response ->
            try {
                when(response){
                    is NetworkResult.Loading ->{
                        _speciesUiState.update {
                            SpeciesUiState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success ->{
                        _speciesUiState.update {
                            SpeciesUiState(species = response.data, isSuccessResult = true)
                        }
                    }

                    is NetworkResult.Error ->{
                        _speciesUiState.update {
                            SpeciesUiState(error = response.message, isErrorResult = true)
                        }
                    }

                    else -> {}
                }
            }catch (t : Throwable){
                if (t !is CancellationException){
                    _speciesUiState.update {
                        SpeciesUiState(error = response.message, isErrorResult = true)
                    }
                }else{
                    throw t
                }
            }

        }.launchIn(viewModelScope)
    }

}