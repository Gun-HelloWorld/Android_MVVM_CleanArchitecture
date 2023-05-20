package com.gun.domain.repository

import com.gun.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface CreatorRepository {
    fun getCreator(creatorId: Int): Flow<Result<List<Creator>>>
    fun getCreatorList(offset: Int, limit: Int): Flow<Result<List<Creator>>>
}