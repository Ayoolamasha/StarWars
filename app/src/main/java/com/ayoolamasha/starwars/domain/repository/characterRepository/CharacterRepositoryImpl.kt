package com.ayoolamasha.starwars.domain.repository.characterRepository

import com.ayoolamasha.starwars.data.mapper.cache.toCharacterEntity
import com.ayoolamasha.starwars.data.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelper
import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val cacheDataSourceHelper: CacheDataSourceHelper
):CharacterRepository {

    override fun getCharacterDetail(
        characterDetailsQueryParams: CharacterDetailsQueryParams
    ): Flow<NetworkResult<Characters>> {
        return flow {
            emit(NetworkResult.Loading())
            val response =
                characterRemoteDataSource.getCharacterDetail(characterUrl = characterDetailsQueryParams.url)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    cacheDataSourceHelper.saveCharacter(charactersEntity = responseUI.toCharacterEntity())

                }
            } else {
                val cachedCharacter =
                    cacheDataSourceHelper.getCharacterByName(characterName = characterDetailsQueryParams.characterName)
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

    override suspend fun deleteAllCharacters() {
        cacheDataSourceHelper.deleteAllCharacters()
    }

    override fun getAllResentSearchCharacter(): Flow<List<Characters>> {
        return cacheDataSourceHelper.getAllCharacter()
    }
}