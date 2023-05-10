package com.gun.domain.model

data class HomeList(
    val characterList: List<Character>?,
    val comicList: List<Comic>?,
    val creatorList: List<Creator>?,
    val eventList: List<Event>?,
    val seriesList: List<Series>?
)