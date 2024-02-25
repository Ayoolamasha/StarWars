package com.ayoolamasha.starwars.featureSearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetails
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsSharedViewModel @Inject constructor(): ViewModel(){
    var characterDetails: CharacterDetails? = null
    var filmDetails: FilmsDetails? = null
}