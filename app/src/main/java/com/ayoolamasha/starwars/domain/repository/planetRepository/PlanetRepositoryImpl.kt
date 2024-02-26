package com.ayoolamasha.starwars.domain.repository.planetRepository

import com.ayoolamasha.starwars.data.mapper.cache.toPlanetEntity
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelper
import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val cacheDataSourceHelper: CacheDataSourceHelper
):PlanetRepository {

    override fun getPlanet(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<Planets>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteDataSource.getPlanet(planetUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheDataSourceHelper.savePlanet(
                        planetEntity = responseUI.toPlanetEntity(
                            characterName = characterDetailsQueryParams.characterName
                        )
                    )
                }
            } else {
                val cachedPlanet =
                    cacheDataSourceHelper.getPlanetByCharacterName(characterName = characterDetailsQueryParams.characterName)
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

    override suspend fun deleteAllPlanets() {
        cacheDataSourceHelper.deleteAllPlanets()
    }
}