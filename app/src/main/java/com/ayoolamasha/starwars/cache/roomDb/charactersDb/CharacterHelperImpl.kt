package com.ayoolamasha.starwars.cache.roomDb.charactersDb

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterHelperImpl @Inject constructor(
    private val characterDao: CharacterDao
): CharacterDaoHelper {
    override suspend fun insertCharacter(charactersEntity: CharactersEntity) {
        return characterDao.insertCharacter(charactersEntity = charactersEntity)
    }

    override suspend fun deleteCharacter(charactersEntity: CharactersEntity) {
        return characterDao.deleteCharacter(charactersEntity = charactersEntity)
    }

    override suspend fun deleteAllCharacters() {
        return characterDao.deleteAllCharacters()
    }

    override fun getAllCharacters(): Flow<List<CharactersEntity>> {
        return characterDao.getAllCharacters()
    }

    override fun getCharacterByName(characterName: String): CharactersEntity {
        return characterDao.getCharacterByName(characterName = characterName)
    }

}