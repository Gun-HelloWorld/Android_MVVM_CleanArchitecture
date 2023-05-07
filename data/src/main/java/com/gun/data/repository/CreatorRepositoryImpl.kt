package com.gun.data.repository

import com.gun.data.datasource.CreatorDataSource
import com.gun.data.mapper.toDomainModel
import com.gun.domain.model.Creator
import com.gun.domain.repository.CreatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreatorRepositoryImpl(
    private val creatorDataSource: CreatorDataSource.Remote
) : CreatorRepository {
    override fun getCreators(page: Int, limit: Int): Flow<Result<List<Creator>>> = flow {
        emit(creatorDataSource.getCreators(page, limit).map { it.toDomainModel() })
    }
}