package com.gun.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.gun.data.datasource.local.MarvelLocalDataSource
import com.gun.data.datasource.remote.MarvelRemoteDataSource
import com.gun.data.datasource.remote.MarvelRemotePagingDataSourceImpl
import com.gun.data.mapper.toDomainModel
import com.gun.data.mapper.toEntity
import com.gun.domain.common.ContentType
import com.gun.domain.model.*
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.model.search.SearchResult
import com.gun.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarvelRepositoryImpl(
    private val marvelLocalDataSource: MarvelLocalDataSource,
    private val marvelRemoteDataSource: MarvelRemoteDataSource,
    private var marvelRemotePagingDataSource: PagingSource<Int, SearchResult>
) : MarvelRepository {

    override fun getSearchResult(
        query: String,
        contentType: ContentType,
        pagingConfig: PagingConfig
    ): Pager<Int, SearchResult> {
        (marvelRemotePagingDataSource as MarvelRemotePagingDataSourceImpl).setContentType(contentType)
        (marvelRemotePagingDataSource as MarvelRemotePagingDataSourceImpl).setQuery(query)

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                marvelRemotePagingDataSource
            }
        )
    }

    override fun getCharacter(characterId: Int): Flow<Result<List<Character>>> = flow {
        emit(marvelRemoteDataSource.getCharacter(characterId).map { it.toDomainModel() })
    }

    override fun getCharacterList(offset: Int, limit: Int): Flow<Result<List<Character>>> = flow {
        emit(marvelRemoteDataSource.getCharacterList(offset, limit).map { it.toDomainModel() })
    }

    override fun getComic(comicId: Int): Flow<Result<List<Comic>>> = flow {
        emit(marvelRemoteDataSource.getComic(comicId).map { it.toDomainModel() })
    }

    override fun getComicList(offset: Int, limit: Int): Flow<Result<List<Comic>>> = flow {
        emit(marvelRemoteDataSource.getComicList(offset, limit).map { it.toDomainModel() })
    }

    override fun getCreator(creatorId: Int): Flow<Result<List<Creator>>> = flow {
        emit(marvelRemoteDataSource.getCreator(creatorId).map { it.toDomainModel() })
    }

    override fun getCreatorList(offset: Int, limit: Int): Flow<Result<List<Creator>>> = flow {
        emit(marvelRemoteDataSource.getCreatorList(offset, limit).map { it.toDomainModel() })
    }

    override fun getEvent(eventId: Int): Flow<Result<List<Event>>> = flow {
        emit(marvelRemoteDataSource.getEvent(eventId).map { it.toDomainModel() })
    }

    override fun getEventList(offset: Int, limit: Int): Flow<Result<List<Event>>> = flow {
        emit(marvelRemoteDataSource.getEventList(offset, limit).map { it.toDomainModel() })
    }

    override fun getSeries(seriesId: Int): Flow<Result<List<Series>>>  = flow {
        emit(marvelRemoteDataSource.getSeries(seriesId).map { it.toDomainModel() })
    }

    override fun getSeriesList(offset: Int, limit: Int): Flow<Result<List<Series>>> = flow {
        emit(marvelRemoteDataSource.getSeriesList(offset, limit).map { it.toDomainModel() })
    }

    override fun getFavoriteList(contentType: ContentType?): Flow<Result<List<Favorite>>> = flow {
        emit(marvelLocalDataSource.getFavoriteList(contentType).map { it.map { f-> f.toDomainModel() } })
    }

    override fun insertFavorite(favorite: Favorite): Flow<Result<Favorite>> = flow {
        emit(marvelLocalDataSource.insertFavorite(favorite.toEntity()).map { it.toDomainModel() })
    }

    override fun deleteFavorite(favorite: Favorite): Flow<Result<Favorite>> = flow {
        emit(marvelLocalDataSource.deleteFavorite(favorite.toEntity()).map { it.toDomainModel() })
    }
}