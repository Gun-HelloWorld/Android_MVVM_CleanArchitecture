package com.gun.domain.repository

import com.gun.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComic(comicId: Int): Flow<Result<List<Comic>>>
    fun getComicList(offset: Int, limit: Int): Flow<Result<List<Comic>>>
}