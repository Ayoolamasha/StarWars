package com.ayoolamasha.starwars.domain.di

import com.ayoolamasha.starwars.domain.repository.characterRepository.CharacterRepository
import com.ayoolamasha.starwars.domain.repository.characterRepository.CharacterRepositoryImpl
import com.ayoolamasha.starwars.domain.repository.characterSearchRepository.CharacterSearchRepository
import com.ayoolamasha.starwars.domain.repository.characterSearchRepository.CharacterSearchRepositoryImpl
import com.ayoolamasha.starwars.domain.repository.filmsRepository.FilmsRepository
import com.ayoolamasha.starwars.domain.repository.filmsRepository.FilmsRepositoryImpl
import com.ayoolamasha.starwars.domain.repository.planetRepository.PlanetRepository
import com.ayoolamasha.starwars.domain.repository.planetRepository.PlanetRepositoryImpl
import com.ayoolamasha.starwars.domain.repository.speciesRepository.SpeciesRepository
import com.ayoolamasha.starwars.domain.repository.speciesRepository.SpeciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DomainInterfaceModule {

    @Binds
    abstract fun providesDomainCharacterRepository(characterSearchRepositoryImpl: CharacterSearchRepositoryImpl): CharacterSearchRepository

    @Binds
    abstract fun providesCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun providesFilmsRepository(filmsRepositoryImpl: FilmsRepositoryImpl): FilmsRepository

    @Binds
    abstract fun providesPlanetRepository(planetRepositoryImpl: PlanetRepositoryImpl): PlanetRepository

    @Binds
    abstract fun providesSpeciesRepository(speciesRepositoryImpl: SpeciesRepositoryImpl): SpeciesRepository
}