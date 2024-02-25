package com.ayoolamasha.starwars.domain

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.planetDb.PlanetDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.recentSearchDb.CharacterDaoHelper
import com.ayoolamasha.starwars.cache.roomDb.speciesDb.SpeciesDaoHelper
import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.cacheRepository.CacheRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.cacheDomain.CharacterCacheDomainRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepository
import com.ayoolamasha.starwars.featureSearch.domain.domainRepository.remoteDomain.CharacterDomainRepositoryImpl
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterDetailsQueryParams
import com.ayoolamasha.starwars.featureSearch.domain.model.CharacterUICase
import com.ayoolamasha.starwars.network.mappers.Either
import com.ayoolamasha.starwars.network.mappers.NetworkResult
import com.ayoolamasha.starwars.network.middleware.MiddlewareProvider
import com.ayoolamasha.starwars.network.model.Failure
import com.ayoolamasha.starwars.network.model.ResponseMessage
import com.ayoolamasha.starwars.utils.fakeCharacterUICase
import com.squareup.moshi.JsonAdapter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test


class CharacterDomainRepositoryTest {
    private val characterDaoHelper: CharacterDaoHelper = mockk()
    private val speciesDaoHelper: SpeciesDaoHelper = mockk()
    private val planetDaoHelper: PlanetDaoHelper = mockk()
    private val filmsDaoHelper: FilmsDaoHelper = mockk()
    private lateinit var characterRemoteRepository: CharacterRemoteRepository
    private lateinit var cacheRepository: CacheRepository
    private lateinit var characterDomainRepository: CharacterDomainRepository
    private val characterRemoteRepositoryImpl: CharacterRemoteRepositoryImpl = mockk(relaxed = true)
    private val cacheRepositoryImpl: CacheRepositoryImpl = mockk(relaxed = true)
    private val characterDomainRepositoryImpl: CharacterDomainRepositoryImpl = mockk(relaxed = true)

    @Before
    fun setUp() {
        characterRemoteRepository = characterRemoteRepositoryImpl
        //cacheRepository = cacheRepositoryImpl
        characterDomainRepository = characterDomainRepositoryImpl
    }

    @Test
    fun `test domainGetCharacterDetail success`() = runBlocking{
        // Arrange
        val characterUrl = "https://swapi.dev/api/people/1/"
        val characterName = "Luke Skywalker"
        val characterDetailsQueryParams = CharacterDetailsQueryParams(characterUrl, characterName)
        val characterUICase = fakeCharacterUICase
        val successResponse = Either.Success(fakeCharacterUICase)
        coEvery { characterRemoteRepository.getCharacterDetail(characterUrl) } returns successResponse

        // Act
        val resultFlow: Flow<NetworkResult<CharacterUICase>> =
            characterDomainRepository.domainGetCharacterDetail(characterDetailsQueryParams)
        val resultList = mutableListOf<NetworkResult<CharacterUICase>>()
        resultFlow.collect { resultList.add(it) }

        // Assert
        assertEquals(2, resultList.size) // Loading + Success
       // assertEquals(NetworkResult.Loading(), resultList[0])
        assertEquals(NetworkResult.Success(data = characterUICase), resultList[1])
        //coVerify(exactly = 1) { cacheRepository.saveCharacter(any()) }
    }
}