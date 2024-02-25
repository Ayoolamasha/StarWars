package com.ayoolamasha.starwars.featureSearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeCharacterCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeCharacterSearchCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeFilmsCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakePlanetCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeSpeciesCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetails
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsDetails
import com.ayoolamasha.starwars.featureSearch.presentation.state.CharacterSearchUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.CharacterUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.FilmsUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.PlanetUiState
import com.ayoolamasha.starwars.featureSearch.presentation.state.SpeciesUiState
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
    private val makeCharacterCallsUseCase: MakeCharacterCallsUseCase,
    private val makePlanetCallsUseCase: MakePlanetCallsUseCase,
    private val makeSpeciesCallsUseCase: MakeSpeciesCallsUseCase,
    private val makeFilmsCallsUseCase: MakeFilmsCallsUseCase,
):ViewModel(){



    private val _characterSearchUiState = MutableStateFlow(CharacterSearchUiState())
    val characterSearchUiState: StateFlow<CharacterSearchUiState> = _characterSearchUiState

    private val _characterUiState = MutableStateFlow(CharacterUiState())
    val characterUiState: StateFlow<CharacterUiState> = _characterUiState

    private val _planetUiState = MutableStateFlow(PlanetUiState())
    val planetUiState: StateFlow<PlanetUiState> = _planetUiState

    private val _speciesUiState = MutableStateFlow(SpeciesUiState())
    val speciesUiState: StateFlow<SpeciesUiState> = _speciesUiState

    private val _filmsUiState = MutableStateFlow(FilmsUiState())
    val filmsUiState: StateFlow<FilmsUiState> = _filmsUiState



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
                            CharacterUiState(characterUICase = response.data, isSuccessResult = true)
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
                            PlanetUiState(planetUiCase = response.data, isSuccessResult = true)
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
                            SpeciesUiState(speciesUiCase = response.data, isSuccessResult = true)
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
                            FilmsUiState(filmsUiCase = response.data, isSuccessResult = true)
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