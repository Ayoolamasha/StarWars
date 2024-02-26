package com.ayoolamasha.starwars.cacheTests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ayoolamasha.starwars.cache.roomDb.SharedRoomDataBase
import com.ayoolamasha.starwars.fakeSpecieEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class SpeciesRoomDbTest {
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
        sharedRoomDataBase.speciesDao().insertSpecies(speciesEntity = fakeSpecieEntity)
        val result = sharedRoomDataBase.speciesDao().getAllSpeciesByCharacter(characterName = "Luke SkyWalker")

        Truth.assertThat(result.characterName).isEqualTo("Luke SkyWalker")
    }

    @Test
    fun testThatDBGetsClearedSuccessFully() = runBlocking {
        sharedRoomDataBase.speciesDao().insertSpecies(speciesEntity = fakeSpecieEntity)
        sharedRoomDataBase.speciesDao().deleteAllSpecies()
        val result = sharedRoomDataBase.speciesDao().getAllSpeciesByCharacter(characterName = "Luke SkyWalker")

        Truth.assertThat(result).isNull()

    }


    @After
    fun tearDown() {
        sharedRoomDataBase.close()
    }
}