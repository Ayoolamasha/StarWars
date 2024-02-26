package com.ayoolamasha.starwars.features.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ayoolamasha.starwars.features.presentation.featureCharacterDetails.model.FilmsDetails
import com.ayoolamasha.starwars.features.presentation.featureSearch.model.CharacterDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel(){
    var characterDetails: CharacterDetails? = null
    var filmDetails: FilmsDetails? = null
}