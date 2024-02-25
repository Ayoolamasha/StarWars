package com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository

import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharacterDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity
import com.ayoolamasha.starwars.featureSearch.data.mapper.toCharacterUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toFilmsUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toPlanetUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toSpeciesUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.featureSearch.presentation.state.CharacterSearchUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val characterDaoHelper: CharacterDaoHelper,
    private val speciesDaoHelper: SpeciesDaoHelper,
    private val planetDaoHelper: PlanetDaoHelper,
    private val filmsDaoHelper: FilmsDaoHelper,
) : CacheRepository {

    /**
     * CHARACTER
     */
    override suspend fun saveCharacter(charactersEntity: CharactersEntity) {
        characterDaoHelper.insertCharacter(charactersEntity = charactersEntity)
    }

    override fun getAllCharacter(): Flow<List<CharacterUICase>> {
        return characterDaoHelper.getAllCharacters().map { mapEntity(it) }

    }

    override fun getCharacterByName(characterName: String): CharacterUICase {
        return characterDaoHelper.getCharacterByName(characterName = characterName)
            .toCharacterUICase()
    }

    override suspend fun deleteAllCharacters() {
        characterDaoHelper.deleteAllCharacters()
    }

    private fun mapEntity(entity: List<CharactersEntity>) : List<CharacterUICase> =
        entity.map { it.toCharacterUICase() }


    /**
     * PLANET
     */

    override suspend fun savePlanet(planetEntity: PlanetEntity) {
        planetDaoHelper.insertPlanet(planetEntity = planetEntity)
    }

    override fun getPlanetByCharacterName(characterName: String): PlanetUiCase {
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

    override fun getSpeciesByCharacterName(characterName: String): SpeciesUiCase {
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

    override fun getFilmsByCharacterName(characterName: String): List<FilmsUiCase> {
        return filmsDaoHelper.getAllFilmsByCharacterName(characterName = characterName)
            .map { it.toFilmsUICase() }
    }

    override suspend fun deleteAllFilms() {
        filmsDaoHelper.deleteAllFilms()
    }
}