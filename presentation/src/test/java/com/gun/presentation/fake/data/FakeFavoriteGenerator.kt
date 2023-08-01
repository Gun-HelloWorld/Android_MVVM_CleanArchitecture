package com.gun.presentation.fake.data

import com.gun.domain.common.*
import com.gun.domain.model.favorite.Favorite

object FakeFavoriteGenerator {

    private val availableContentType = mutableListOf(
        SeriesType,
        ComicType,
        EventType,
        CharacterType,
        CreatorType
    )

    fun generate(contentType: ContentType?, count: Int): List<Favorite> {
        val fakeFavoriteList = mutableListOf<Favorite>()

        for (i in 1 until count) {
            fakeFavoriteList.add(Favorite(
                id = i,
                name = "name$i",
                thumbnailPath = "thumbnailPath$i",
                thumbnailExtension = "thumbnailExtension$i",
                contentType = contentType ?: availableContentType.random()
            ))
        }

        return fakeFavoriteList
    }

    fun generateOnOfEachByFilterAll(): List<Favorite> {
        val fakeFavoriteList = mutableListOf<Favorite>()

        for (i in 0 until availableContentType.size) {
            fakeFavoriteList.add(
                Favorite(
                    id = i,
                    name = "name$i",
                    thumbnailPath = "thumbnailPath$i",
                    thumbnailExtension = "thumbnailExtension$i",
                    contentType = availableContentType[i]
                )
            )
        }

        return fakeFavoriteList
    }
}