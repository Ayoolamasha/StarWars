package com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository


import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.network.mappers.Either
import com.ayoolamasha.starwars.network.model.Failure

interface CharacterRemoteRepository {

    suspend fun searchCharacters(searchParam: String): Either<Failure, List<CharacterUICase>>

    suspend fun getCharacterDetail(characterUrl: String): Either<Failure, CharacterUICase>

    suspend fun getFilmDetails(filmsUrl: String): Either<Failure, List<FilmsUiCase>>

    suspend fun getSpecieDetails(speciesUrl: String): Either<Failure, SpeciesUiCase>

    suspend fun getPlanet(planetUrl: String): Either<Failure, PlanetUiCase>
}