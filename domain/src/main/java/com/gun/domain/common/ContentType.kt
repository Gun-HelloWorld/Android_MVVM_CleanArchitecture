package com.gun.domain.common

import java.io.Serializable

sealed class ContentType : Serializable

object CharacterType: ContentType()
object ComicType: ContentType()
object SeriesType: ContentType()
object EventType: ContentType()
object CreatorType: ContentType()

fun ContentType.name(): String {
    return this.javaClass.simpleName
}

fun String.parseToContentType(): ContentType {
    return when(this) {
        CharacterType.name() -> CharacterType
        ComicType.name() -> ComicType
        SeriesType.name() -> SeriesType
        EventType.name() -> EventType
        CreatorType.name() -> CreatorType
        else -> throw IllegalArgumentException("InvalidType. type : $this")
    }
}