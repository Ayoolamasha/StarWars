package com.ayoolamasha.starwars.data.repository.remoteDataSource

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.data.mapper.remote.toCharacterSearchUiCase
import com.ayoolamasha.starwars.data.mapper.remote.toCharacterUICase
import com.ayoolamasha.starwars.data.mapper.remote.toFilmsUICase
import com.ayoolamasha.starwars.data.mapper.remote.toPlanetUICase
import com.ayoolamasha.starwars.data.mapper.remote.toSpeciesUICase
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.domain.model.Characters
import com.ayoolamasha.starwars.domain.model.Films
import com.ayoolamasha.starwars.domain.model.Planets
import com.ayoolamasha.starwars.domain.model.Species
import com.ayoolamasha.starwars.network.extensions.call
import com.ayoolamasha.starwars.network.mappers.Either
import com.ayoolamasha.starwars.network.middleware.MiddlewareProvider
import com.ayoolamasha.starwars.network.model.Failure
import com.ayoolamasha.starwars.network.model.ResponseMessage
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRemoteDataSourceImpl @Inject constructor(
    private val middlewareProvider: MiddlewareProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseMessage>,
    private val searchApiServiceHelper: SearchApiServiceHelper
) : CharacterRemoteDataSource {

    override suspend fun searchCharacters(searchParam: String): Either<Failure, List<Characters>> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                searchApiServiceHelper.searchCharacters(
                    searchParam = searchParam
                )
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess {  responseList ->
                responseList?.let { response ->
                    response.toCharacterSearchUiCase()

                }
            }

        } as Either<Failure, List<Characters>>
    }

    override suspend fun getCharacterDetail(characterUrl: String): Either<Failure, Characters> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                searchApiServiceHelper.getCharacterDetail(
                    characterUrl = characterUrl
                )
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess {  responseList ->
                responseList?.let { response ->
                    response.toCharacterUICase()

                }
            }

        } as Either<Failure, Characters>
    }

    override suspend fun getFilmDetails(filmsUrl: String): Either<Failure, List<Films>> {
           return call(
                middleWares = middlewareProvider.getAll(),
                ioDispatcher = ioDispatcher,
                adapter = errorAdapter,
                retrofitCall = {
                    searchApiServiceHelper.getFilmDetails(
                        filmsUrl = filmsUrl
                    )
                }
            ).let { response ->
                response.mapSuccess { responseData ->
                    responseData
                }.coMapSuccess {  responseList ->
                    responseList?.let { response ->
                        response.toFilmsUICase()

                    }
                }

            } as Either<Failure, List<Films>>
        }

    override suspend fun getSpecieDetails(speciesUrl: String): Either<Failure, Species> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                searchApiServiceHelper.getSpecieDetails(
                    speciesUrl = speciesUrl
                )
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess {  responseList ->
                responseList?.let { response ->
                    response.toSpeciesUICase()

                }
            }

        } as Either<Failure, Species>
    }

    override suspend fun getPlanet(planetUrl: String): Either<Failure, Planets> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                searchApiServiceHelper.getPlanet(
                    planetUrl = planetUrl
                )
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess {  responseList ->
                responseList?.let { response ->
                    response.toPlanetUICase()

                }
            }

        } as Either<Failure, Planets>
    }
}
