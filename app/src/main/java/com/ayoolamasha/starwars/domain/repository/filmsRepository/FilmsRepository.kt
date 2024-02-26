package com.ayoolamasha.starwars.domain.repository.filmsRepository

import com.ayoolamasha.starwars.data.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    fun getFilmDetails(characterFilmsQueryParams: CharacterFilmsQueryParams): Flow<NetworkResult<List<Films>>>

    suspend fun deleteAllFilms()
}