package com.gun.data.datasource

import com.gun.data.entity.creator.CreatorDto

class CreatorDataSource {
    interface Remote {
        suspend fun getCreators(page: Int, limit: Int): Result<CreatorDto>
    }
}