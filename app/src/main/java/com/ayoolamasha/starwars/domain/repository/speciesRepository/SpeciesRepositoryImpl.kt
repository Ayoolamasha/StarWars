package com.ayoolamasha.starwars.domain.repository.speciesRepository

import com.ayoolamasha.starwars.data.mapper.cache.toSpeciesEntity
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelper
import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val cacheDataSourceHelper: CacheDataSourceHelper
): SpeciesRepository{

    override fun getSpecieDetails(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<Species>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteDataSource.getSpecieDetails(speciesUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheDataSourceHelper.saveSpecies(
                        speciesEntity = responseUI.toSpeciesEntity(
                            characterName = characterDetailsQueryParams.characterName
                        )
                    )
                }
            } else {
                val cachedSpecies =
                    cacheDataSourceHelper.getSpeciesByCharacterName(characterName = characterDetailsQueryParams.characterName)
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

    override suspend fun deleteAllSpecies() {
        cacheDataSourceHelper.deleteAllSpecies()
    }

}