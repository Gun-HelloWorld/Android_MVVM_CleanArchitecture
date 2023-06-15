package com.gun.domain.usecase

import com.gun.domain.repository.MarvelRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchDataUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetDataUseCase.GetSearchDataUseCase {

    override fun invoke(param: GetDataUseCase.GetSearchDataUseCase.GetSearchParams) =
        marvelRepository.getSearchResult(
            param.query,
            param.contentType,
            param.pagingConfig
        ).flow
}