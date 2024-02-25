package com.ayoolamasha.starwars.featureSearch.domain.calls_usecase

import com.ayoolamasha.starwars.di.IoDispatcher
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterFilmsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.network.BaseUseCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeCharacterSearchCallsUseCase @Inject constructor(
    private val characterDomainRepository: CharacterDomainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<String, NetworkResult<List<CharacterUICase>>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: String?): Flow<NetworkResult<List<CharacterUICase>>> {
        requireParams(params)
        return characterDomainRepository.domainSearchCharacters(searchParam = params)
    }
}

@Singleton
class MakeCharacterCallsUseCase @Inject constructor(
    private val characterDomainRepository: CharacterDomainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<CharacterUICase>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<CharacterUICase>> {
        requireParams(params)
        return characterDomainRepository.domainGetCharacterDetail(characterDetailsQueryParams = params)
    }
}


@Singleton
class MakeFilmsCallsUseCase @Inject constructor(
    private val characterDomainRepository: CharacterDomainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterFilmsQueryParams, NetworkResult<List<FilmsUiCase>>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterFilmsQueryParams?): Flow<NetworkResult<List<FilmsUiCase>>> {
        requireParams(params)
        return characterDomainRepository.domainGetFilmDetails(characterFilmsQueryParams = params)
    }
}

@Singleton
class MakeSpeciesCallsUseCase @Inject constructor(
    private val characterDomainRepository: CharacterDomainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<SpeciesUiCase>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<SpeciesUiCase>> {
        requireParams(params)
        return characterDomainRepository.domainGetSpecieDetails(characterDetailsQueryParams = params)
    }
}


@Singleton
class MakePlanetCallsUseCase @Inject constructor(
    private val characterDomainRepository: CharacterDomainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseUseCase<CharacterDetailsQueryParams, NetworkResult<PlanetUiCase>>(){
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: CharacterDetailsQueryParams?): Flow<NetworkResult<PlanetUiCase>> {
        requireParams(params)
        return characterDomainRepository.domainGetPlanet(characterDetailsQueryParams = params)
    }
}