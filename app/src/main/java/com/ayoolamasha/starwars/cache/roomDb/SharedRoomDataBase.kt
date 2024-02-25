package com.ayoolamasha.starwars.cache.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayoolamasha.starwars.cache.CustomTypeConverter
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDao
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDao
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetEntity
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharacterDao
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharactersEntity
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDao
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesEntity

@Database(
    entities = [
        CharactersEntity::class,
        FilmsEntity::class,
        PlanetEntity::class,
        SpeciesEntity::class,
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(CustomTypeConverter::class)
abstract class SharedRoomDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun speciesDao(): SpeciesDao

    abstract fun planetDao(): PlanetDao

    abstract fun filmsDao(): FilmsDao
}