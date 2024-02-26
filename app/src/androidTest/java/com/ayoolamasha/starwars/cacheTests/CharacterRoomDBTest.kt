package com.ayoolamasha.starwars.cacheTests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ayoolamasha.starwars.cache.roomDb.SharedRoomDataBase
import com.ayoolamasha.starwars.fakeCharacterEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class CharacterRoomDBTest {
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
    fun testThatDataGetsInsertedIntoDB() = runBlocking {
        sharedRoomDataBase.characterDao().insertCharacter(charactersEntity = fakeCharacterEntity)
        val result = sharedRoomDataBase.characterDao().getAllCharacters().firstOrNull()

        assertThat(result).isNotNull()
    }

    @Test
    fun testThatDBReturnsCharacterInfoByName() = runBlocking {
        sharedRoomDataBase.characterDao().insertCharacter(charactersEntity = fakeCharacterEntity)
        val result = sharedRoomDataBase.characterDao().getCharacterByName(characterName = "Luke SkyWalker")

        assertThat(result.characterName).isEqualTo("Luke SkyWalker")
    }

    @Test
    fun testThatDBGetsClearedSuccessFully() = runBlocking {
        sharedRoomDataBase.characterDao().insertCharacter(charactersEntity = fakeCharacterEntity)
        sharedRoomDataBase.characterDao().deleteAllCharacters()
        val result = sharedRoomDataBase.characterDao().getAllCharacters().firstOrNull()

        assertThat(result).isEmpty()

    }


    @After
    fun tearDown() {
        sharedRoomDataBase.close()
    }
}