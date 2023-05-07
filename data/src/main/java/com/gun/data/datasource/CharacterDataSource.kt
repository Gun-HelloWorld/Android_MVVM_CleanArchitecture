package com.gun.data.datasource

import com.gun.data.entity.character.CharactersDto

class CharacterDataSource {
    interface Remote {
        suspend fun getCharacters(page: Int, limit: Int): Result<CharactersDto>
    }
}
