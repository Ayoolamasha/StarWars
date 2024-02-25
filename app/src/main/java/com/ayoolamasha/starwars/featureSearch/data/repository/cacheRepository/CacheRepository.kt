package com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository

import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    /**
     * CHARACTERS
     */
    suspend fun saveCharacter(charactersEntity: CharactersEntity)

    fun getAllCharacter(): Flow<List<CharacterUICase>>

    fun getCharacterByName(characterName: String): CharacterUICase

    suspend fun deleteAllCharacters()

    /**
     * PLANET
     */
    suspend fun savePlanet(planetEntity: PlanetEntity)

    fun getPlanetByCharacterName(characterName: String): PlanetUiCase

    suspend fun deleteAllPlanets()

    /**
     * SPECIES
     */

    suspend fun saveSpecies(speciesEntity: SpeciesEntity)

    fun getSpeciesByCharacterName(characterName: String): SpeciesUiCase

    suspend fun deleteAllSpecies()

    /**
     * FILMS
     */

    suspend fun saveFilms(filmsEntity: List<FilmsEntity>)

    fun getFilmsByCharacterName(characterName: String): List<FilmsUiCase>

    suspend fun deleteAllFilms()
}