package com.ayoolamasha.starwars.domain

import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeCharacterCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeCharacterSearchCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeFilmsCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakePlanetCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.calls_usecase.MakeSpeciesCallsUseCase
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.featureSearch.domain.model.FilmsUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.PlanetUiCase
import com.ayoolamasha.starwars.featureSearch.domain.model.SpeciesUiCase
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.utils.fakeCharacterDetailsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacterFilmsQueryParams
import com.ayoolamasha.starwars.utils.fakeCharacterModelResponse
import com.ayoolamasha.starwars.utils.fakeCharacterUICase
import com.ayoolamasha.starwars.utils.fakeFilmsUICase
import com.ayoolamasha.starwars.utils.fakePlanetUiCase
import com.ayoolamasha.starwars.utils.fakeSpeciesUICase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class CharacterUseCaseTest {
    private val characterDomainRepository: CharacterDomainRepository = mockk()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val characterSearchUseCase = MakeCharacterSearchCallsUseCase(characterDomainRepository = characterDomainRepository, ioDispatcher =  ioDispatcher)
    private val characterUseCase = MakeCharacterCallsUseCase(characterDomainRepository = characterDomainRepository, ioDispatcher =  ioDispatcher)
    private val planetUseCase = MakePlanetCallsUseCase(characterDomainRepository = characterDomainRepository, ioDispatcher =  ioDispatcher)
    private val speciesUseCase = MakeSpeciesCallsUseCase(characterDomainRepository = characterDomainRepository, ioDispatcher =  ioDispatcher)
    private val filmsUseCase = MakeFilmsCallsUseCase(characterDomainRepository = characterDomainRepository, ioDispatcher =  ioDispatcher)


    @Test
    fun  `test that makeCharacterSearchCall returns success result`() = runBlocking {
        val characterSearchData = listOf(fakeCharacterUICase)

        coEvery { characterDomainRepository.domainSearchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns flowOf(NetworkResult.Success(characterSearchData))

        val result = characterSearchUseCase.execute(params = "Luke SkyWalker")

        val resultList = mutableListOf<NetworkResult<List<CharacterUICase>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeCharacterSearchCall returns error result`() = runBlocking {

        coEvery { characterDomainRepository.domainSearchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns flowOf(NetworkResult.Error("Test error"))


        val result = characterSearchUseCase.execute(params = "Luke SkyWalker")

        val resultList = mutableListOf<NetworkResult<List<CharacterUICase>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }


    @Test
    fun  `test that makeCharacterCall returns success result`() = runBlocking {
        val characterData = fakeCharacterUICase

        coEvery { characterDomainRepository.domainGetCharacterDetail(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Success(characterData))

        val result = characterUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<CharacterUICase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeCharacterCall returns error result`() = runBlocking {

        coEvery { characterDomainRepository.domainGetCharacterDetail(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Error("Test error"))


        val result = characterUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<CharacterUICase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }

    @Test
    fun  `test that makePlanetCall returns success result`() = runBlocking {
        val characterPlanetData = fakePlanetUiCase

        coEvery { characterDomainRepository.domainGetPlanet(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Success(characterPlanetData))

        val result = planetUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<PlanetUiCase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makePlanetCall returns error result`() = runBlocking {

        coEvery { characterDomainRepository.domainGetPlanet(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Error("Test error"))


        val result = planetUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<PlanetUiCase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }

    @Test
    fun  `test that makeSpeciesCall returns success result`() = runBlocking {
        val characterSpeciesData = fakeSpeciesUICase

        coEvery { characterDomainRepository.domainGetSpecieDetails(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Success(characterSpeciesData))

        val result = speciesUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<SpeciesUiCase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeSpeciesCall returns error result`() = runBlocking {

        coEvery { characterDomainRepository.domainGetSpecieDetails(characterDetailsQueryParams = fakeCharacterDetailsQueryParams) } returns flowOf(NetworkResult.Error("Test error"))


        val result = speciesUseCase.execute(params = fakeCharacterDetailsQueryParams)

        val resultList = mutableListOf<NetworkResult<SpeciesUiCase>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }

    @Test
    fun  `test that makeFilmsCall returns success result`() = runBlocking {
        val characterFilmsData = listOf(fakeFilmsUICase)

        coEvery { characterDomainRepository.domainGetFilmDetails(characterFilmsQueryParams = fakeCharacterFilmsQueryParams) } returns flowOf(NetworkResult.Success(characterFilmsData))

        val result = filmsUseCase.execute(params = fakeCharacterFilmsQueryParams)

        val resultList = mutableListOf<NetworkResult<List<FilmsUiCase>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `test that makeFilmsCall returns error result`() = runBlocking {

        coEvery { characterDomainRepository.domainGetFilmDetails(characterFilmsQueryParams = fakeCharacterFilmsQueryParams) } returns flowOf(NetworkResult.Error("Test error"))


        val result = filmsUseCase.execute(params = fakeCharacterFilmsQueryParams)

        val resultList = mutableListOf<NetworkResult<List<FilmsUiCase>>>()
        result.collect{resultList.add(it)}

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}