package com.gun.domain.repository

import com.gun.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComics(page: Int, limit: Int): Flow<Result<List<Comic>>>
}