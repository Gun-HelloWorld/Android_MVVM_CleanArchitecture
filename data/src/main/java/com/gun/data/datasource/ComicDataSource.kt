package com.gun.data.datasource

import com.gun.data.entity.comic.ComicDto

class ComicDataSource {
    interface Remote {
        suspend fun getComics(page: Int, limit: Int): Result<ComicDto>
    }
}