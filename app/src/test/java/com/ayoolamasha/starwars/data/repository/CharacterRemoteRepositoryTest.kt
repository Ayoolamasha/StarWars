package com.ayoolamasha.starwars.data.repository

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterModelResponse
import com.ayoolamasha.starwars.featureSearch.data.model.CharacterSearchResponse
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepository
import com.ayoolamasha.starwars.featureSearch.data.repository.remoteRepository.CharacterRemoteRepositoryImpl
import com.ayoolamasha.starwars.network.middleware.MiddlewareProvider
import com.ayoolamasha.starwars.network.model.ResponseMessage
import com.ayoolamasha.starwars.network.model.getErrorMessage
import com.ayoolamasha.starwars.utils.CustomResponseBody
import com.ayoolamasha.starwars.utils.fakeCharacterModelResponse
import com.ayoolamasha.starwars.utils.fakeCharacterSearchResponse
import com.ayoolamasha.starwars.utils.fakeCharacterUICase
import com.ayoolamasha.starwars.utils.fakeFilmsResponse
import com.ayoolamasha.starwars.utils.fakeFilmsUICase
import com.ayoolamasha.starwars.utils.fakePlanetResponse
import com.ayoolamasha.starwars.utils.fakePlanetUiCase
import com.ayoolamasha.starwars.utils.fakeSpeciesResponse
import com.ayoolamasha.starwars.utils.fakeSpeciesUICase
import com.squareup.moshi.JsonAdapter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CharacterRemoteRepositoryTest {
    private val middlewareProvider: MiddlewareProvider = mockk()
    private val ioDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    private val errorAdapter: JsonAdapter<ResponseMessage> = mockk()
    private val searchApiServiceHelper: SearchApiServiceHelper = mockk()
    private lateinit var characterRemoteRepository: CharacterRemoteRepository


    @Before
    fun setUp(){
        characterRemoteRepository = CharacterRemoteRepositoryImpl(
            middlewareProvider = middlewareProvider,
            ioDispatcher = ioDispatcher,
            errorAdapter = errorAdapter,
            searchApiServiceHelper = searchApiServiceHelper
        )
    }

    @Test
    fun `test that search character returns success result`() = runBlocking{
        val response = Response.success(fakeCharacterSearchResponse)

        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns response

        val networkCall = characterRemoteRepository.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString())

        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) }

        assert(networkCall.isSuccess)
        assertEquals(listOf(fakeCharacterUICase), networkCall.getSuccessOrNull())

    }

    @Test
    fun `test that search character returns failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<CharacterSearchResponse>(400, customResponseBody)


        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) } returns response


        val networkCall = characterRemoteRepository.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString())


        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.searchCharacters(searchParam = fakeCharacterModelResponse.name.toString()) }


        assert(networkCall.isError)
        assertEquals("An error occurred while processing your request.",   networkCall.getFailureOrNull()?.getErrorMessage())

    }



    @Test
    fun `test that get character returns success result`() = runBlocking{
        val response = Response.success(fakeCharacterModelResponse)

        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString()) } returns response

        val networkCall = characterRemoteRepository.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString())

        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString()) }

        assert(networkCall.isSuccess)
        assertEquals(fakeCharacterUICase, networkCall.getSuccessOrNull())

    }

    @Test
    fun `test that get character returns failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<CharacterModelResponse>(400, customResponseBody)


        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString()) } returns response


        val networkCall = characterRemoteRepository.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString())


        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getCharacterDetail(characterUrl = fakeCharacterModelResponse.url.toString()) }


        assert(networkCall.isError)
        assertEquals("An error occurred while processing your request.",   networkCall.getFailureOrNull()?.getErrorMessage())

    }




    @Test
    fun `test that get planet returns success result`() = runBlocking{
        val response = Response.success(fakePlanetResponse)

        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString()) } returns response

        val networkCall = characterRemoteRepository.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString())

        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString()) }

        assert(networkCall.isSuccess)
        assertEquals(fakePlanetUiCase, networkCall.getSuccessOrNull())

    }

    @Test
    fun `test that get planet returns failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<PlanetResponse>(400, customResponseBody)


        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString()) } returns response


        val networkCall = characterRemoteRepository.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString())


        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getPlanet(planetUrl = fakeCharacterModelResponse.homeWorld.toString()) }


        assert(networkCall.isError)
        assertEquals("An error occurred while processing your request.",   networkCall.getFailureOrNull()?.getErrorMessage())

    }



    @Test
    fun `test that get species returns success result`() = runBlocking{
        val response = Response.success(fakeSpeciesResponse)

        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString()) } returns response

        val networkCall = characterRemoteRepository.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString())

        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString()) }

        assert(networkCall.isSuccess)
        assertEquals(fakeSpeciesUICase, networkCall.getSuccessOrNull())

    }

    @Test
    fun `test that get species returns failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<SpeciesResponse>(400, customResponseBody)


        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString()) } returns response


        val networkCall = characterRemoteRepository.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString())


        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getSpecieDetails(speciesUrl = fakeCharacterModelResponse.species.toString()) }


        assert(networkCall.isError)
        assertEquals("An error occurred while processing your request.",   networkCall.getFailureOrNull()?.getErrorMessage())

    }


    @Test
    fun `test that get films returns success result`() = runBlocking{
        val response = Response.success(fakeFilmsResponse)

        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString()) } returns response

        val networkCall = characterRemoteRepository.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString())

        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString()) }

        assert(networkCall.isSuccess)
        assertEquals(listOf(fakeFilmsUICase), networkCall.getSuccessOrNull())

    }

    @Test
    fun `test that get films returns failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<FilmsResponse>(400, customResponseBody)


        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { searchApiServiceHelper.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString()) } returns response


        val networkCall = characterRemoteRepository.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString())


        verify { middlewareProvider.getAll() }
        coVerify { searchApiServiceHelper.getFilmDetails(filmsUrl = fakeCharacterModelResponse.films.toString()) }


        assert(networkCall.isError)
        assertEquals("An error occurred while processing your request.",   networkCall.getFailureOrNull()?.getErrorMessage())

    }



}