package com.gun.data.repository

import com.gun.data.datasource.ComicDataSource
import com.gun.data.mapper.toDomainModel
import com.gun.domain.model.Comic
import com.gun.domain.repository.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ComicRepositoryImpl(
    private val comicDataSource: ComicDataSource.Remote
) : ComicRepository {
    override fun getComics(page: Int, limit: Int): Flow<Result<List<Comic>>> = flow {
        emit(comicDataSource.getComics(page, limit).map { it.toDomainModel() })
    }
}