package com.gun.data.datasource

import com.gun.data.entity.comic.ComicDto

class ComicDataSource {

    interface Remote {
        suspend fun getComic(comicId: Int): Result<ComicDto>
        suspend fun getComicList(offset: Int, limit: Int): Result<ComicDto>
    }

}