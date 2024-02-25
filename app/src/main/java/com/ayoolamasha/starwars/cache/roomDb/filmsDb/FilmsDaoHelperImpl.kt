package com.ayoolamasha.starwars.cache.roomDb.filmsDb

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsDaoHelperImpl @Inject constructor(
    private val filmsDao: FilmsDao
):FilmsDaoHelper{
    override suspend fun insertFilm(filmsEntity: List<FilmsEntity>) {
        return filmsDao.insertFilm(filmsEntity = filmsEntity)
    }

    override suspend fun deleteAllFilms() {
        return filmsDao.deleteAllFilms()
    }

    override fun getAllFilmsByCharacterName(characterName: String): List<FilmsEntity> {
        return filmsDao.getAllFilmsByCharacterName(characterName = characterName)
    }
}