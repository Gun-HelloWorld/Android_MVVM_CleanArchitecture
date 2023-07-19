package com.gun.presentation.fake.usecase

import com.gun.domain.common.ContentType
import com.gun.domain.model.detail.ContentDetail
import com.gun.domain.usecase.GetUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetDetailDataUseCaseImpl : GetUseCase.GetDetailDataUseCase {
    private val fakeFlow = MutableSharedFlow<Result<ContentDetail>>()
    suspend fun emit(value: Result<ContentDetail>) = fakeFlow.emit(value)

    override fun invoke(contentId: Int, contentType: ContentType) = fakeFlow
}