package com.ayoolamasha.starwars.domain.repository.characterSearchRepository


import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterSearchRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
) : CharacterSearchRepository {

    override fun searchCharacters(searchParam: String): Flow<NetworkResult<List<Characters>>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = characterRemoteDataSource.searchCharacters(searchParam = searchParam)

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









}