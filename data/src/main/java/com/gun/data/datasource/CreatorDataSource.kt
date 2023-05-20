package com.gun.data.datasource

import com.gun.data.entity.creator.CreatorDto

class CreatorDataSource {

    interface Remote {
        suspend fun getCreator(creatorId: Int): Result<CreatorDto>
        suspend fun getCreatorList(offset: Int, limit: Int): Result<CreatorDto>
    }

}