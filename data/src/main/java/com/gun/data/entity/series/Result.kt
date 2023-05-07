package com.gun.data.entity.series

data class Result(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String?,
    val endYear: Int,
    val events: Events,
    val id: Int,
    val modified: String,
    val next: Next,
    val previous: Any,
    val rating: String,
    val resourceURI: String,
    val startYear: Int,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val type: String,
    val urls: List<Url>
)