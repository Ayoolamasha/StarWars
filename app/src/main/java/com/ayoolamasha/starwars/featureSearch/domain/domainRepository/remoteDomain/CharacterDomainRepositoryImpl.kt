package com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain


import com.ayoolamasha.starwars.featureSearch.data.mapper.toCharacterEntity
import com.ayoolamasha.starwars.featureSearch.data.mapper.toFilmsListEntity
import com.ayoolamasha.starwars.featureSearch.data.mapper.toPlanetEntity
import com.ayoolamasha.starwars.featureSearch.data.mapper.toSpeciesEntity
import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepository
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDomainRepositoryImpl @Inject constructor(
    private val characterRemoteRepository: CharacterRemoteRepository,
    private val cacheRepository: CacheRepository,
) : CharacterDomainRepository {

    override fun domainSearchCharacters(searchParam: String): Flow<NetworkResult<List<CharacterUICase>>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = characterRemoteRepository.searchCharacters(searchParam = searchParam)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(responseUI))
                }
            } else {
                val failure = response.getFailureOrNull()
                if (failure != null) {
                    emit(NetworkResult.Error(failure.getErrorMessage()))
                }
            }
        }
    }

    override fun domainGetCharacterDetail(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<CharacterUICase>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteRepository.getCharacterDetail(characterUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheRepository.saveCharacter(charactersEntity = responseUI.toCharacterEntity())

                }
            } else {
                val cachedCharacter =
                    cacheRepository.getCharacterByName(characterName = characterDetailsQueryParams.characterName)
                if (cachedCharacter.name != null) {
                    emit(NetworkResult.Success(data = cachedCharacter))
                } else {
                    val failure = response.getFailureOrNull()
                    if (failure != null) {
                        emit(NetworkResult.Error(message = failure.getErrorMessage()))
                    }
                }
            }
        }
    }

    override fun domainGetFilmDetails(
        characterFilmsQueryParams: CharacterFilmsQueryParams
    ): Flow<NetworkResult<List<FilmsUiCase>>> {
        return flow {
            emit(NetworkResult.Loading())
            val filmsUiCases = mutableListOf<FilmsUiCase>()
            var hasError = false
            characterFilmsQueryParams.url?.forEach { url ->
                val response = characterRemoteRepository.getFilmDetails(filmsUrl = url)

                if (response.isSuccess) {
                    val responseUI = response.getSuccessOrNull()
                    if (responseUI != null) {
                        filmsUiCases.addAll(responseUI)
                    }
                } else {
                    val cachedFilms =
                        cacheRepository.getFilmsByCharacterName(characterName = characterFilmsQueryParams.characterName)
                    if (cachedFilms.isEmpty()) {
                        val failure = response.getFailureOrNull()
                        if (failure != null) {
                            hasError = true
                            emit(NetworkResult.Error(message = failure.getErrorMessage()))
                        }
                    } else {
                        emit(NetworkResult.Success(data = cachedFilms))
                    }
                }
            }

            if (!hasError) {
                emit(NetworkResult.Success(filmsUiCases))
                cacheRepository.saveFilms(
                    filmsEntity = filmsUiCases.toFilmsListEntity(
                        characterName = characterFilmsQueryParams.characterName
                    )
                )
            }
        }
    }


    override fun domainGetSpecieDetails(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<SpeciesUiCase>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteRepository.getSpecieDetails(speciesUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheRepository.saveSpecies(
                        speciesEntity = responseUI.toSpeciesEntity(
                            characterName = characterDetailsQueryParams.characterName
                        )
                    )
                }
            } else {
                val cachedSpecies =
                    cacheRepository.getSpeciesByCharacterName(characterName = characterDetailsQueryParams.characterName)
                if (cachedSpecies.name != null) {
                    emit(NetworkResult.Success(data = cachedSpecies))
                } else {
                    val failure = response.getFailureOrNull()
                    if (failure != null) {
                        emit(NetworkResult.Error(message = failure.getErrorMessage()))
                    }
                }
            }
        }
    }

    override fun domainGetPlanet(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<PlanetUiCase>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteRepository.getPlanet(planetUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheRepository.savePlanet(
                        planetEntity = responseUI.toPlanetEntity(
                            characterName = characterDetailsQueryParams.characterName
                        )
                    )
                }
            } else {
                val cachedPlanet =
                    cacheRepository.getPlanetByCharacterName(characterName = characterDetailsQueryParams.characterName)
                if (cachedPlanet.name != null) {
                    emit(NetworkResult.Success(data = cachedPlanet))
                } else {
                    val failure = response.getFailureOrNull()
                    if (failure != null) {
                        emit(NetworkResult.Error(message = failure.getErrorMessage()))
                    }
                }
            }
        }
    }
}