package com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain

import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharacterDomainRepository {
     fun domainSearchCharacters(searchParam: String): Flow<NetworkResult<List<CharacterUICase>>>

     fun domainGetCharacterDetail(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<CharacterUICase>>

     fun domainGetFilmDetails(characterFilmsQueryParams: CharacterFilmsQueryParams): Flow<NetworkResult<List<FilmsUiCase>>>

     fun domainGetSpecieDetails(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<SpeciesUiCase>>

     fun domainGetPlanet(characterDetailsQueryParams: CharacterDetailsQueryParams): Flow<NetworkResult<PlanetUiCase>>

}