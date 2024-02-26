package com.ayoolamasha.starwars.data.repository.cacheDataSource

import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharacterDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.data.mapper.cache.toCharacterUICase
import com.ayoolamasha.starwars.data.mapper.cache.toFilmsUICase
import com.ayoolamasha.starwars.data.mapper.cache.toPlanetUICase
import com.ayoolamasha.starwars.data.mapper.cache.toSpeciesUICase
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.model.Species
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheDataSourceHelperImpl @Inject constructor(
    private val characterDaoHelper: CharacterDaoHelper,
    private val speciesDaoHelper: SpeciesDaoHelper,
    private val planetDaoHelper: PlanetDaoHelper,
    private val filmsDaoHelper: FilmsDaoHelper,
) : CacheDataSourceHelper {

    /**
     * CHARACTER
     */
    override suspend fun saveCharacter(charactersEntity: CharactersEntity) {
        characterDaoHelper.insertCharacter(charactersEntity = charactersEntity)
    }

    override fun getAllCharacter(): Flow<List<Characters>> {
        return characterDaoHelper.getAllCharacters().map { mapEntity(it) }

    }

    override fun getCharacterByName(characterName: String): Characters {
        return characterDaoHelper.getCharacterByName(characterName = characterName)
            .toCharacterUICase()
    }

    override suspend fun deleteAllCharacters() {
        characterDaoHelper.deleteAllCharacters()
    }

    private fun mapEntity(entity: List<CharactersEntity>) : List<Characters> =
        entity.map { it.toCharacterUICase() }


    /**
     * PLANET
     */

    override suspend fun savePlanet(planetEntity: PlanetEntity) {
        planetDaoHelper.insertPlanet(planetEntity = planetEntity)
    }

    override fun getPlanetByCharacterName(characterName: String): Planets {
        return planetDaoHelper.getAllPlanetsByCharacterName(characterName = characterName)
            .toPlanetUICase()
    }

    override suspend fun deleteAllPlanets() {
        planetDaoHelper.deleteAllPlanets()
    }

    /**
     * SPECIES
     */

    override suspend fun saveSpecies(speciesEntity: SpeciesEntity) {
        speciesDaoHelper.insertSpecies(speciesEntity = speciesEntity)
    }

    override fun getSpeciesByCharacterName(characterName: String): Species {
        return speciesDaoHelper.getAllSpeciesByCharacter(characterName = characterName)
            .toSpeciesUICase()
    }

    override suspend fun deleteAllSpecies() {
        speciesDaoHelper.deleteAllSpecies()
    }

    /**
     * FILMS
     */

    override suspend fun saveFilms(filmsEntity: List<FilmsEntity>) {
        filmsDaoHelper.insertFilm(filmsEntity = filmsEntity)
    }

    override fun getFilmsByCharacterName(characterName: String): List<Films> {
        return filmsDaoHelper.getAllFilmsByCharacterName(characterName = characterName)
            .map { it.toFilmsUICase() }
    }

    override suspend fun deleteAllFilms() {
        filmsDaoHelper.deleteAllFilms()
    }
}