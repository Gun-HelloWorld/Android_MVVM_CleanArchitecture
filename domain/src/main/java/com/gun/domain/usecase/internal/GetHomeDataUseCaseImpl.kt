package com.gun.domain.usecase.internal

import com.gun.domain.model.*
import com.gun.domain.repository.*
import com.gun.domain.usecase.HomeDataUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetHomeDataUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val comicRepository: ComicRepository,
    private val creatorRepository: CreatorRepository,
    private val eventRepository: EventRepository,
    private val seriesRepository: SeriesRepository
) : HomeDataUseCase.GetHomeDataUseCase {

    override fun invoke(offset: Int, limit: Int): Flow<Result<HomeList>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        combine(
            characterRepository.getCharacterList(offset, limit),
            comicRepository.getComicList(offset, limit),
            creatorRepository.getCreatorList(offset, limit),
            eventRepository.getEventList(offset, limit),
            seriesRepository.getSeriesList(offset, limit)
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