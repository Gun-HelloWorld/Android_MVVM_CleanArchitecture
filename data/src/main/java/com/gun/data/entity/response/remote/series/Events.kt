package com.gun.data.entity.response.remote.series

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<EventSummary>,
    val returned: Int
)