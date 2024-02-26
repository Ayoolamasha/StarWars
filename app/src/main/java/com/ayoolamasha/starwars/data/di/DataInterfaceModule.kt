package com.ayoolamasha.starwars.data.di


import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataInterfaceModule {
    @Binds
    abstract fun providesCharacterRepository(characterRemoteRepositoryImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

}