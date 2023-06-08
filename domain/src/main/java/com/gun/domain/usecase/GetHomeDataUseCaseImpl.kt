package com.gun.domain.usecase

import com.gun.domain.model.Character
import com.gun.domain.model.Comic
import com.gun.domain.model.Creator
import com.gun.domain.model.Event
import com.gun.domain.model.Series
import com.gun.domain.model.home.HomeList
import com.gun.domain.repository.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetHomeDataUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetDataUseCase.GetHomeDataUseCase {

    override fun invoke(offset: Int, limit: Int): Flow<Result<HomeList>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        combine(
            marvelRepository.getCharacterList(offset, limit),
            marvelRepository.getComicList(offset, limit),
            marvelRepository.getCreatorList(offset, limit),
            marvelRepository.getEventList(offset, limit),
            marvelRepository.getSeriesList(offset, limit)
        ) { characterResult: Result<List<Character>>,
            comicResult: Result<List<Comic>>,
            creatorResult: Result<List<Creator>>,
            eventResult: Result<List<Event>>,
            seriesResult: Result<List<Series>> ->
            {
                if (characterResult.isFailure && comicResult.isFailure && creatorResult.isFailure && eventResult.isFailure && seriesResult.isFailure) {
                    Result.failure(NoSuchElementException())
                } else {
                    Result.success(
                        HomeList(
                            characterResult.getOrNull(),
                            comicResult.getOrNull(),
                            creatorResult.getOrNull(),
                            eventResult.getOrNull(),
                            seriesResult.getOrNull()
                        )
                    )
                }
            }
        }.collect {
            emit(it.asFlow().single())
        }
    }

}