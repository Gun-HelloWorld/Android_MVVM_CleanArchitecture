package com.gun.domain.repository

import com.gun.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface CreatorRepository {
    fun getCreators(page: Int, limit: Int): Flow<Result<List<Creator>>>
}