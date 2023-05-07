package com.gun.data.mapper

import com.gun.data.entity.character.CharactersDto
import com.gun.domain.model.Character

fun CharactersDto.toDomainModel(): List<Character> {
    return data.results.map {
        Character(
            id = it.id,
            name = it.name,
            description = it.description ?: "",
            thumbnailPath = it.thumbnail.path,
            thumbnailExtension = it.thumbnail.extension,
            copyright = copyright,
            attributionText = attributionText
        )
    }
}