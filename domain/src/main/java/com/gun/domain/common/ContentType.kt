package com.gun.domain.common

sealed class ContentType

object CharacterType: ContentType()
object ComicType: ContentType()
object SeriesType: ContentType()
object EventType: ContentType()
object CreatorType: ContentType()