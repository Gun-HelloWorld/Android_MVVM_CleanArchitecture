package com.gun.data.datasource

import com.gun.data.entity.character.CharactersDto

class CharacterDataSource {

    interface Remote {
        suspend fun getCharacter(characterId: Int): Result<CharactersDto>
        suspend fun getCharacterList(offset: Int, limit: Int): Result<CharactersDto>
    }

}
