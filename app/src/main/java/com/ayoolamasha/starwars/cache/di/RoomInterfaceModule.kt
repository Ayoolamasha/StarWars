package com.ayoolamasha.starwars.cache.di

import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharacterDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharacterHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelperImpl
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelperImpl
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelper
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RoomInterfaceModule {

    @Binds
    abstract fun providesCacheRepository(cacheRepositoryImpl: CacheDataSourceHelperImpl): CacheDataSourceHelper

    @Binds
    abstract fun providesCharacterDaoHelper(characterHelperImpl: CharacterHelperImpl): CharacterDaoHelper

    @Binds
    abstract fun providesSpeciesDaoHelper(speciesDaoHelperImpl: SpeciesDaoHelperImpl): SpeciesDaoHelper

    @Binds
    abstract fun providesPlanetDaoHelper(planetDaoHelperImpl: PlanetDaoHelperImpl): PlanetDaoHelper

    @Binds
    abstract fun providesFilmsDaoHelper(filmsDaoHelperImpl: FilmsDaoHelperImpl): FilmsDaoHelper
}