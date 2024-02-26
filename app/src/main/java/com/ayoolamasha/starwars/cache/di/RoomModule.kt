package com.ayoolamasha.starwars.cache.di

import android.content.Context
import androidx.room.Room
import com.ayoolamasha.starwars.cache.roomDb.SharedRoomDataBase
import com.ayoolamasha.starwars.cache.roomDb.charactersDb.CharacterDao
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDao
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDao
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDao
import com.ayoolamasha.starwars.core.SHARED_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideProductListDatabase(@ApplicationContext context: Context): SharedRoomDataBase {
        return Room.databaseBuilder(
            context,
            SharedRoomDataBase::class.java,
            SHARED_DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesCharacterDao(sharedRoomDataBase: SharedRoomDataBase): CharacterDao{
        return sharedRoomDataBase.characterDao()
    }

    @Singleton
    @Provides
    fun providesSpeciesDao(sharedRoomDataBase: SharedRoomDataBase): SpeciesDao{
        return sharedRoomDataBase.speciesDao()
    }

    @Singleton
    @Provides
    fun providesPlanetDao(sharedRoomDataBase: SharedRoomDataBase): PlanetDao{
        return sharedRoomDataBase.planetDao()
    }

    @Singleton
    @Provides
    fun providesFilmsDao(sharedRoomDataBase: SharedRoomDataBase): FilmsDao{
        return sharedRoomDataBase.filmsDao()
    }
}