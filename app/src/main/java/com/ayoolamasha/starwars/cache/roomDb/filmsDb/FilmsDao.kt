package com.ayoolamasha.starwars.cache.roomDb.filmsDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilm(filmsEntity: List<FilmsEntity>)

    @Query("DELETE FROM FilmsEntity")
    suspend fun deleteAllFilms()

    @Query("SELECT * FROM FilmsEntity WHERE characterName = :characterName")
    fun getAllFilmsByCharacterName(characterName: String) : List<FilmsEntity>

}