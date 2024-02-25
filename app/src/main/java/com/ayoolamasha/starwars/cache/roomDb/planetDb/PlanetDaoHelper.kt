package com.ayoolamasha.starwars.cache.roomDb.planetDb


interface PlanetDaoHelper {

    suspend fun insertPlanet(planetEntity: PlanetEntity)

    suspend fun deleteAllPlanets()

    fun getAllPlanetsByCharacterName(characterName: String): PlanetEntity
}