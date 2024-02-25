package com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.featureSearch.data.mapper.toCharacterSearchUiCase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toCharacterUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toFilmsUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toPlanetUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toSpeciesUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
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
class CharacterRemoteRepositoryImpl @Inject constructor(
    private val middlewareProvider: MiddlewareProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseMessage>,
    private val searchApiServiceHelper: SearchApiServiceHelper

) : CharacterRemoteRepository {

    override suspend fun searchCharacters(searchParam: String): Either<Failure, List<CharacterUICase>> {
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

        } as Either<Failure, List<CharacterUICase>>
    }

    override suspend fun getCharacterDetail(characterUrl: String): Either<Failure, CharacterUICase> {
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

        } as Either<Failure, CharacterUICase>
    }

    override suspend fun getFilmDetails(filmsUrl: String): Either<Failure, List<FilmsUiCase>> {
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

            } as Either<Failure, List<FilmsUiCase>>
        }

    override suspend fun getSpecieDetails(speciesUrl: String): Either<Failure, SpeciesUiCase> {
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

        } as Either<Failure, SpeciesUiCase>
    }

    override suspend fun getPlanet(planetUrl: String): Either<Failure, PlanetUiCase> {
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

        } as Either<Failure, PlanetUiCase>
    }
}
