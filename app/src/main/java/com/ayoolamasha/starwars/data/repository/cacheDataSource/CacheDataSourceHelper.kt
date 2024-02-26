package com.ayoolamasha.starwars.data.repository.cacheDataSource

import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.model.Species
import kotlinx.coroutines.flow.Flow

interface CacheDataSourceHelper {
    /**
     * CHARACTERS
     */
    suspend fun saveCharacter(charactersEntity: CharactersEntity)

    fun getAllCharacter(): Flow<List<Characters>>

    fun getCharacterByName(characterName: String): Characters

    suspend fun deleteAllCharacters()

    /**
     * PLANET
     */
    suspend fun savePlanet(planetEntity: PlanetEntity)

    fun getPlanetByCharacterName(characterName: String): Planets

    suspend fun deleteAllPlanets()

    /**
     * SPECIES
     */

    suspend fun saveSpecies(speciesEntity: SpeciesEntity)

    fun getSpeciesByCharacterName(characterName: String): Species

    suspend fun deleteAllSpecies()

    /**
     * FILMS
     */

    suspend fun saveFilms(filmsEntity: List<FilmsEntity>)

    fun getFilmsByCharacterName(characterName: String): List<Films>

    suspend fun deleteAllFilms()
}