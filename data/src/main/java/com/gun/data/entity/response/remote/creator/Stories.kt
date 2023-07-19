package com.gun.data.entity.response.remote.creator

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)