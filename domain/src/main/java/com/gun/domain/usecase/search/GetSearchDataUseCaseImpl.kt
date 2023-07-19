package com.gun.domain.usecase.search

import com.gun.domain.repository.MarvelRepository
import com.gun.domain.usecase.GetUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchDataUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetUseCase.GetSearchDataUseCase {

    override fun invoke(param: GetUseCase.GetSearchDataUseCase.GetSearchParams) =
        marvelRepository.getSearchResult(
            param.query,
            param.contentType,
            param.pagingConfig
        ).flow
}