package com.ayoolamasha.starwars.data.mapper

import com.ayoolamasha.starwars.featureSearch.data.mapper.toCharacterUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toFilmsUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toPlanetUICase
import com.ayoolamasha.starwars.featureSearch.data.mapper.toSpeciesUICase
import com.ayoolamasha.starwars.featureSearch.data.model.FilmsResponse
import com.ayoolamasha.starwars.featureSearch.data.model.PlanetResponse
import com.ayoolamasha.starwars.featureSearch.data.model.SpeciesResponse
import com.ayoolamasha.starwars.utils.fakeCharacterModelResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `test that toCharacterUICase converts CharacterModelResponse to CharacterUICase`(){
        val characterModelResponse = fakeCharacterModelResponse

        val toCharacterUICase = characterModelResponse.toCharacterUICase()


        assertEquals("Luke SkyWalker", toCharacterUICase.name)
        assertEquals("19BBY", toCharacterUICase.birthYear)
        assertEquals("172", toCharacterUICase.height)
        assertEquals(listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/6/"
        ), toCharacterUICase.films)
        assertEquals("https://swapi.dev/api/planets/1/", toCharacterUICase.homeWorld)
        assertEquals(listOf(""), toCharacterUICase.species)
        assertEquals("https://swapi.dev/api/people/1/", toCharacterUICase.url)
    }

    @Test
    fun `test that toPlanetUiCase converts PlanetResponse to PlanetUICase`(){
        val planetResponse = PlanetResponse(
            name = "Earth",
            population = "7000000000"
        )

        val toPlanetUICase = planetResponse.toPlanetUICase()

        assertEquals("Earth", toPlanetUICase.name)
        assertEquals("7000000000", toPlanetUICase.population)
    }

    @Test
    fun `test that toSpeciesUICase converts SpeciesResponse to SpeciesUICase`(){
        val speciesResponse = SpeciesResponse(
            name = "Human",
            language = "English",
            homeWorld = "Earth"
        )

        val toSpeciesUiCase = speciesResponse.toSpeciesUICase()

        assertEquals("Human", toSpeciesUiCase.name)
        assertEquals("English", toSpeciesUiCase.language)
        assertEquals("Earth", toSpeciesUiCase.homeWorld)
    }

    @Test
    fun `test that toFilmUiCase converts FilmsResponse to List Of FilmUICase`(){
        val filmResponse = FilmsResponse(
            title = "A New Hope",
            openingCrawl = "Giving New Hope"
        )

        val toFilmsUiCase = filmResponse.toFilmsUICase()


        assertEquals("A New Hope", toFilmsUiCase[0].title)
        assertEquals("Giving New Hope", toFilmsUiCase[0].openingCrawl)
    }
}