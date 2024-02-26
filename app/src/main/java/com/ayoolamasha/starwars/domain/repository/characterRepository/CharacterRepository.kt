package com.ayoolamasha.starwars.domain.repository.characterRepository

import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterDetail(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<Characters>>

    suspend fun deleteAllCharacters()

    fun getAllResentSearchCharacter(): Flow<List<Characters>>
}