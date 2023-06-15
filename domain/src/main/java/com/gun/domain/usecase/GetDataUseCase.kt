package com.gun.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gun.domain.common.ContentType
import com.gun.domain.model.detail.ContentDetail
import com.gun.domain.model.home.HomeList
import com.gun.domain.model.search.SearchResult
import kotlinx.coroutines.flow.Flow

interface GetDataUseCase {

    interface GetHomeDataUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<HomeList>>
    }

    interface GetDetailDataUseCase {
        operator fun invoke(contentId: Int, contentType: ContentType): Flow<Result<ContentDetail>>
    }

    interface GetSearchDataUseCase {
        operator fun invoke(param: GetSearchParams): Flow<PagingData<SearchResult>>

        data class GetSearchParams(
            val query: String,
            val pagingConfig: PagingConfig,
            val contentType: ContentType
        )
    }

}