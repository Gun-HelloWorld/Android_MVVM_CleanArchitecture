package com.gun.presentation.fake.data

import com.gun.domain.model.Character
import com.gun.domain.model.SimpleInfo

object FakeCharacterGenerator {

    fun generate(id: Int): Character {
        return Character(
            id = id,
            name = "CharacterName$id",
            description = "CharacterDesc$id",
            thumbnailPath = "CharacterThumbnailPath$id",
            thumbnailExtension = "CharacterThumbnailExtension$id",
            detailUrl = "CharacterDetailUrl$id",
            copyright = "CharacterCopyright$id",
            attributionText = "CharacterAttributionText$id",
            comicInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            seriesInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            storyInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            eventInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
        )
    }

    fun generate(offset: Int, limit: Int): List<Character> {
        val fakeCharacterList = mutableListOf<Character>()

        for (i in offset until limit) {
            fakeCharacterList.add(
                Character(
                    id = i,
                    name = "CharacterName$i",
                    description = "CharacterDesc$i",
                    thumbnailPath = "CharacterThumbnailPath$i",
                    thumbnailExtension = "CharacterThumbnailExtension$i",
                    detailUrl = "CharacterDetailUrl$i",
                    copyright = "CharacterCopyright$i",
                    attributionText = "CharacterAttributionText$i",
                    comicInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    seriesInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    storyInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    eventInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                )
            )
        }

        return fakeCharacterList
    }
}