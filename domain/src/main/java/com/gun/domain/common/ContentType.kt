package com.gun.domain.common

import java.io.Serializable

sealed class ContentType : Serializable

object CharacterType: ContentType()
object ComicType: ContentType()
object SeriesType: ContentType()
object EventType: ContentType()
object CreatorType: ContentType()