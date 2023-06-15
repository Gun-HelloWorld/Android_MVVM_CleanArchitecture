package com.gun.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gun.data.mapper.toPagingModelOfSearch
import com.gun.data.network.MarvelApi
import com.gun.domain.common.*
import com.gun.domain.model.search.PagingModel
import com.gun.domain.model.search.SearchResult

class MarvelRemotePagingDataSourceImpl(
    private val marvelApi: MarvelApi
) : PagingSource<Int, SearchResult>() {

    private var contentType: ContentType = CharacterType

    private lateinit var query: String

    fun setContentType(contentType: ContentType) {
        this.contentType = contentType
    }

    fun setQuery(query: String) {
        this.query = query
    }

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        return try {
            val current = params.key ?: 0

            val response = when(contentType) {
                is SeriesType -> marvelApi.getSeriesList(current, 20, query).toPagingModelOfSearch()
                is ComicType -> marvelApi.getComicList(current, 20, query).toPagingModelOfSearch()
                is EventType -> marvelApi.getEventList(current, 20, query).toPagingModelOfSearch()
                is CharacterType -> marvelApi.getCharacterList(current, 20, query).toPagingModelOfSearch()
                is CreatorType -> marvelApi.getCreatorList(current, 20, query).toPagingModelOfSearch()
            }

            LoadResult.Page(
                data = response.list?: emptyList(),
                prevKey = null,
                nextKey = nextKey(response)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        return null
    }

    private fun nextKey(response: PagingModel<SearchResult>?): Int? {
        if (response == null || response.list.isNullOrEmpty()) {
            return null
        }

        val offset = response.offset
        val limit = response.limit
        val total = response.total

        val nextKey = if (total - (offset + limit) > 0) {
            offset + limit
        } else {
            null
        }

        return nextKey
    }
}