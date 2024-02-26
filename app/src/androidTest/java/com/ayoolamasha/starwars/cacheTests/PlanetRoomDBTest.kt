package com.ayoolamasha.starwars.cacheTests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ayoolamasha.starwars.cache.roomDb.SharedRoomDataBase
import com.ayoolamasha.starwars.fakePlanetEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class PlanetRoomDBTest {
    private lateinit var sharedRoomDataBase: SharedRoomDataBase

    @Before
    fun setUp(){
        sharedRoomDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SharedRoomDataBase::class.java
        ).allowMainThreadQueries()
            .build()
    }


    @Test
    fun testThatDBReturnsCharacterInfoByName() = runBlocking {
        sharedRoomDataBase.planetDao().insertPlanet(planetEntity = fakePlanetEntity)
        val result = sharedRoomDataBase.planetDao().getAllPlanetsByCharacterName(characterName = "Luke SkyWalker")

        Truth.assertThat(result.characterName).isEqualTo("Luke SkyWalker")
    }

    @Test
    fun testThatDBGetsClearedSuccessFully() = runBlocking {
        sharedRoomDataBase.planetDao().insertPlanet(planetEntity = fakePlanetEntity )
        sharedRoomDataBase.planetDao().deleteAllPlanets()
        val result = sharedRoomDataBase.planetDao().getAllPlanetsByCharacterName(characterName = "Luke SkyWalker")

        Truth.assertThat(result).isNull()

    }


    @After
    fun tearDown() {
        sharedRoomDataBase.close()
    }
}