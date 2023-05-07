package com.gun.data.entity.comic

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<CharacterSummary>,
    val returned: Int
)