package com.ayoolamasha.starwars.domain.repository.characterSearchRepository

import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharacterSearchRepository {
     fun searchCharacters(searchParam: String): Flow<NetworkResult<List<Characters>>>

}