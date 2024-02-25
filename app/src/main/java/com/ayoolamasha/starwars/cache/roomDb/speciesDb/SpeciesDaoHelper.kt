package com.ayoolamasha.starwars.cache.roomDb.speciesDb


interface SpeciesDaoHelper {

    suspend fun insertSpecies(speciesEntity: SpeciesEntity)

    suspend  fun deleteAllSpecies()

    fun getAllSpeciesByCharacter(characterName: String): SpeciesEntity
}