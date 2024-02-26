package com.ayoolamasha.starwars.cache.roomDb.charactersDb

import kotlinx.coroutines.flow.Flow


interface CharacterDaoHelper {
    suspend fun insertCharacter(charactersEntity: CharactersEntity)

    suspend fun deleteCharacter(charactersEntity: CharactersEntity)

    suspend fun deleteAllCharacters()

    fun getAllCharacters(): Flow<List<CharactersEntity>>

    fun getCharacterByName(characterName: String): CharactersEntity
}