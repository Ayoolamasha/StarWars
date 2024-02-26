package com.ayoolamasha.starwars.data.mapper

import com.ayoolamasha.starwars.data.mapper.remote.toCharacterUICase
import com.ayoolamasha.starwars.data.mapper.remote.toFilmsUICase
import com.ayoolamasha.starwars.data.mapper.remote.toPlanetUICase
import com.ayoolamasha.starwars.data.mapper.remote.toSpeciesUICase
import com.ayoolamasha.starwars.data.model.FilmsResponse
import com.ayoolamasha.starwars.data.model.PlanetResponse
import com.ayoolamasha.starwars.data.model.SpeciesResponse
import com.ayoolamasha.starwars.utils.fakeCharacterModelResponse
import com.ayoolamasha.starwars.utils.fakeExpectedCharacterModelResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `test that toCharacterUICase converts CharacterModelResponse to CharacterUICase`(){
        val characterModelResponse = fakeCharacterModelResponse

        val toCharacterUICase = characterModelResponse.toCharacterUICase()
        val expectedData = fakeExpectedCharacterModelResponse.toCharacterUICase()



        assertEquals(expectedData, toCharacterUICase)
    }

    @Test
    fun `test that toPlanetUiCase converts PlanetResponse to PlanetUICase`(){
        val planetResponse = PlanetResponse(
            name = "Earth",
            population = "7000000000"
        )

        val expectedResponse = PlanetResponse(
            name = "Earth",
            population = "7000000000"
        )

        val toPlanetUICase = planetResponse.toPlanetUICase()
        val expectedData = expectedResponse.toPlanetUICase()

        assertEquals(expectedData, toPlanetUICase)
    }

    @Test
    fun `test that toSpeciesUICase converts SpeciesResponse to SpeciesUICase`(){
        val speciesResponse = SpeciesResponse(
            name = "Human",
            language = "English",
            homeWorld = "Earth"
        )

        val expectedResponse = SpeciesResponse(
            name = "Human",
            language = "English",
            homeWorld = "Earth"
        )

        val toSpeciesUiCase = speciesResponse.toSpeciesUICase()
        val expectedData = expectedResponse.toSpeciesUICase()

        assertEquals(expectedData, toSpeciesUiCase)

    }

    @Test
    fun `test that toFilmUiCase converts FilmsResponse to List Of FilmUICase`(){
        val filmResponse = FilmsResponse(
            title = "A New Hope",
            openingCrawl = "Giving New Hope"
        )

        val expectedResponse = FilmsResponse(
            title = "A New Hope",
            openingCrawl = "Giving New Hope"
        )

        val toFilmsUiCase = filmResponse.toFilmsUICase()

        val expectedData = expectedResponse.toFilmsUICase()


        assertEquals(expectedData, toFilmsUiCase)

    }
}