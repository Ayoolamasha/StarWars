package com.ayoolamasha.starwars.domain.repository.filmsRepository

import com.ayoolamasha.starwars.data.mapper.cache.toFilmsListEntity
import com.ayoolamasha.starwars.data.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.data.repository.cacheDataSource.CacheDataSourceHelper
import com.ayoolamasha.starwars.data.repository.remoteDataSource.CharacterRemoteDataSource
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val cacheDataSourceHelper: CacheDataSourceHelper
):FilmsRepository {

    override fun getFilmDetails(
        characterFilmsQueryParams: CharacterFilmsQueryParams
    ): Flow<NetworkResult<List<Films>>> {
        return flow {
            emit(NetworkResult.Loading())
            val films = mutableListOf<Films>()
            var hasError = false

            if (characterFilmsQueryParams.url != null){
                for (charactersUrl in characterFilmsQueryParams.url){
                    val response = characterRemoteDataSource.getFilmDetails(filmsUrl = charactersUrl)

                    if (response.isSuccess) {
                        val responseUI = response.getSuccessOrNull()
                        if (responseUI != null) {
                            films.addAll(responseUI)
                        }
                    }
                    else {
                        val cachedFilms =
                            cacheDataSourceHelper.getFilmsByCharacterName(characterName = characterFilmsQueryParams.characterName)
                        if (cachedFilms.isEmpty()) {
                            val failure = response.getFailureOrNull()
                            if (failure != null) {
                                hasError = true
                                emit(NetworkResult.Error(message = failure.getErrorMessage()))
                            }
                        } else {
                            emit(NetworkResult.Success(data = cachedFilms))
                        }
                        break
                    }
                }
            }
            if (!hasError) {
                emit(NetworkResult.Success(films))
                cacheDataSourceHelper.saveFilms(
                    filmsEntity = films.toFilmsListEntity(
                        characterName = characterFilmsQueryParams.characterName
                    )
                )
            }
        }
    }

    override suspend fun deleteAllFilms() {
        cacheDataSourceHelper.deleteAllFilms()
    }
}