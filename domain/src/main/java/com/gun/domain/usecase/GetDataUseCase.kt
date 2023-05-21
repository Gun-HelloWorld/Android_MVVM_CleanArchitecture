package com.gun.domain.usecase

import com.gun.domain.common.ContentType
import com.gun.domain.model.detail.ContentDetail
import com.gun.domain.model.home.HomeList
import kotlinx.coroutines.flow.Flow

interface GetDataUseCase {

    interface GetHomeDataUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<HomeList>>
    }

    interface GetDetailDataUseCase {
        operator fun invoke(contentId: Int, contentType: ContentType): Flow<Result<ContentDetail>>
    }

}