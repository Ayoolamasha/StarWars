package com.ayoolamasha.starwars.di

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharacterDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharacterHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelperImpl
import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain.CharacterCacheDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain.CharacterCacheDomainRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class InterfaceModule {

    /**
     * API SERVICE BINDS
     */
    @Binds
    abstract fun providesSearchApiServiceHelper(searchApiServiceHelperImpl: SearchApiServiceHelperImpl): SearchApiServiceHelper

    /**
     * REMOTE REPOSITORY BINDS
     */
    @Binds
    abstract fun providesCharacterRepository(characterRemoteRepositoryImpl: CharacterRemoteRepositoryImpl): CharacterRemoteRepository




    /**
     * DOMAIN REPOSITORY BINDS
     */
    @Binds
    abstract fun providesDomainCharacterRepository(domainRepositoryImpl: CharacterDomainRepositoryImpl): CharacterDomainRepository

    @Binds
    abstract fun providesDomainCacheRepository(characterCacheDomainRepositoryImpl: CharacterCacheDomainRepositoryImpl): CharacterCacheDomainRepository

    /**
     * CACHE BINDS
     */

    @Binds
    abstract fun providesCacheRepository(cacheRepositoryImpl: CacheRepositoryImpl): CacheRepository

    @Binds
    abstract fun providesCharacterDaoHelper(characterHelperImpl: CharacterHelperImpl): CharacterDaoHelper

    @Binds
    abstract fun providesSpeciesDaoHelper(speciesDaoHelperImpl: SpeciesDaoHelperImpl): SpeciesDaoHelper

    @Binds
    abstract fun providesPlanetDaoHelper(planetDaoHelperImpl: PlanetDaoHelperImpl): PlanetDaoHelper

    @Binds
    abstract fun providesFilmsDaoHelper(filmsDaoHelperImpl: FilmsDaoHelperImpl): FilmsDaoHelper

}