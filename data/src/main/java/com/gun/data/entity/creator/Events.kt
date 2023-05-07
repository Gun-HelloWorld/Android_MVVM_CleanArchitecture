package com.gun.data.entity.creator

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<EventSummary>,
    val returned: Int
)