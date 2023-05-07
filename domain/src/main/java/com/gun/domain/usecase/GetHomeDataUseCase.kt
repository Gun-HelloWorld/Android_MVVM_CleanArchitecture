package com.gun.domain.usecase

import com.gun.domain.model.*
import com.gun.domain.repository.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetHomeDataUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val comicRepository: ComicRepository,
    private val creatorRepository: CreatorRepository,
    private val eventRepository: EventRepository,
    private val seriesRepository: SeriesRepository
) {
    operator fun invoke(page: Int, limit: Int): Flow<Result<HomeList>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }


        combine(
            characterRepository.getCharacters(page, limit),
            comicRepository.getComics(page, limit),
            creatorRepository.getCreators(page, limit),
            eventRepository.getEvents(page, limit),
            seriesRepository.getSeries(page, limit)
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