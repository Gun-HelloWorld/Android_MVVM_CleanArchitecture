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

    override fun getCreator(creatorId: Int): Flow<Result<List<Creator>>> = flow {
        emit(creatorDataSource.getCreator(creatorId).map { it.toDomainModel() })
    }

    override fun getCreatorList(offset: Int, limit: Int): Flow<Result<List<Creator>>> = flow {
        emit(creatorDataSource.getCreatorList(offset, limit).map { it.toDomainModel() })
    }

}