package com.gun.presentation.fake.data

import com.gun.domain.common.*
import com.gun.domain.model.mapper.toContentDetail

object FakeContentDetailGenerator {

    fun generate(id: Int, contentType: ContentType) = when (contentType) {
        is CharacterType -> {
            FakeCharacterGenerator.generate(id).toContentDetail()
        }
        is ComicType -> {
            FakeComicGenerator.generate(id).toContentDetail()
        }
        is SeriesType -> {
            FakeSeriesGenerator.generate(id).toContentDetail()
        }
        is EventType -> {
            FakeEventGenerator.generate(id).toContentDetail()
        }
        is CreatorType -> {
            FakeCreatorGenerator.generate(id).toContentDetail()
        }
    }

}