package com.ayoolamasha.starwars.cache.roomDb.planetDb

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetDaoHelperImpl @Inject constructor(
    private val planetDao: PlanetDao
):PlanetDaoHelper{
    override suspend fun insertPlanet(planetEntity: PlanetEntity) {
        return planetDao.insertPlanet(planetEntity = planetEntity)
    }

    override suspend fun deleteAllPlanets() {
        return planetDao.deleteAllPlanets()
    }

    override fun getAllPlanetsByCharacterName(characterName: String): PlanetEntity {
        return planetDao.getAllPlanetsByCharacterName(characterName = characterName)
    }
}