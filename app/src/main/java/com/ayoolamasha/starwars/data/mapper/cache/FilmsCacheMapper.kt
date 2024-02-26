package com.ayoolamasha.starwars.data.mapper.cache

import com.ayoolamasha.starwars.cache.roomDb.filmsDb.FilmsEntity
import com.ayoolamasha.starwars.domain.model.Films

fun FilmsEntity.toFilmsUICase(): Films {
    return Films(
        title = filmTitle,
        openingCrawl = filmOpeningCrawl,
    )
}

fun List<Films>.toFilmsListEntity(characterName: String): List<FilmsEntity> {
    return this.map {
        toFilmsEntity(
            title = it.title,
            openingCrawl = it.openingCrawl,
            characterName= characterName
        )
    }
}


fun toFilmsEntity(title: String?, openingCrawl: String?, characterName: String): FilmsEntity {
    return FilmsEntity(
        filmTitle = title,
        filmOpeningCrawl = openingCrawl,
        characterName = characterName
    )
}