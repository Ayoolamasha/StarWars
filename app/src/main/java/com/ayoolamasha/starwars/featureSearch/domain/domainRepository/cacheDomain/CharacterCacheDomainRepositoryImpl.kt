package com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain

import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepository
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterCacheDomainRepositoryImpl @Inject constructor(
    private val cacheRepository: CacheRepository
):CharacterCacheDomainRepository {

    /**
     * CHARACTER
     */

    override fun domainGetAllCharacter(): Flow<List<CharacterUICase>> {
        return cacheRepository.getAllCharacter()
    }

    override suspend fun domainDeleteAllCharacters() {
         cacheRepository.deleteAllCharacters()
    }

    /**
     * PLANET
     */

    override suspend fun domainDeleteAllPlanets() {
         cacheRepository.deleteAllPlanets()
    }

    /**
     * SPECIES
     */

    override suspend fun domainDeleteAllSpecies() {
         cacheRepository.deleteAllSpecies()
    }

    /**
     * FILMS
     */

    override suspend fun domainDeleteAllFilms() {
        cacheRepository.deleteAllFilms()
    }


}