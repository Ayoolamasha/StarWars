package com.ayoolamasha.starwars.data.repository.remoteDataSource


import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.network.mappers.Either
import com.ayoolamasha.starwars.network.model.Failure

interface CharacterRemoteDataSource {

    suspend fun searchCharacters(searchParam: String): Either<Failure, List<Characters>>

    suspend fun getCharacterDetail(characterUrl: String): Either<Failure, Characters>

    suspend fun getFilmDetails(filmsUrl: String): Either<Failure, List<Films>>

    suspend fun getSpecieDetails(speciesUrl: String): Either<Failure, Species>

    suspend fun getPlanet(planetUrl: String): Either<Failure, Planets>
}