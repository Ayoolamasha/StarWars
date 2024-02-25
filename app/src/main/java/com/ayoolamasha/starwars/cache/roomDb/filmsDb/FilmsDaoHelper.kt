package com.ayoolamasha.starwars.cache.roomDb.filmsDb


interface FilmsDaoHelper {

    suspend fun insertFilm(filmsEntity: List<FilmsEntity>)

    suspend fun deleteAllFilms()

    fun getAllFilmsByCharacterName(characterName: String) : List<FilmsEntity>
}