package com.ayoolamasha.starwars.cache.roomDb.speciesDb

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesDaoHelperImpl @Inject constructor(
    private val speciesDao: SpeciesDao
): SpeciesDaoHelper{

    override suspend fun insertSpecies(speciesEntity: SpeciesEntity) {
        return speciesDao.insertSpecies(speciesEntity = speciesEntity)
    }

    override suspend fun deleteAllSpecies() {
        return speciesDao.deleteAllSpecies()
    }

    override fun getAllSpeciesByCharacter(characterName: String): SpeciesEntity {
        return speciesDao.getAllSpeciesByCharacter(characterName = characterName)
    }
}