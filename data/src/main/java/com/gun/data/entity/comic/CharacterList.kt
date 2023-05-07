package com.gun.data.entity.comic

data class CharacterList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<CharacterSummary>
)