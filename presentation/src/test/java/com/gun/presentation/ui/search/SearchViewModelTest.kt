package com.gun.presentation.ui.search

import androidx.paging.*
import app.cash.turbine.test
import com.gun.data.database.MarvelDao
import com.gun.data.datasource.local.MarvelLocalDataSourceImpl
import com.gun.data.datasource.remote.MarvelRemoteDataSourceImpl
import com.gun.data.datasource.remote.MarvelRemotePagingDataSourceImpl
import com.gun.data.network.MarvelApi
import com.gun.data.repository.MarvelRepositoryImpl
import com.gun.domain.common.*
import com.gun.domain.model.mapper.parseFavorite
import com.gun.domain.model.search.SearchResult
import com.gun.domain.usecase.search.GetSearchDataUseCaseImpl
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyCharactersDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyComicDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyCreatorDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyEventDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptySeriesDto
import com.gun.presentation.fake.data.FakeSearchResultGenerator
import com.gun.presentation.fake.usecase.FakeDeleteFavoriteUseCaseImpl
import com.gun.presentation.fake.usecase.FakeGetFavoriteDataUseCaseImpl
import com.gun.presentation.fake.usecase.FakeInsertFavoriteUseCaseImpl
import com.gun.presentation.test.TestPagingDataConsumer
import com.gun.presentation.ui.common.ResultEmptyType
import com.gun.presentation.ui.common.ResultErrorType
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@Suppress("NonAsciiCharacters")
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var searchViewModel: SearchViewModel

    private val testPagingDataConsumer = TestPagingDataConsumer<SearchResult>()

    private val mockMarvelApi = mockk<MarvelApi>()
    private val mockMarveDao = mockk<MarvelDao>()

    private val fakeGetFavoriteUseCaseImpl = FakeGetFavoriteDataUseCaseImpl()
    private val fakeInsertFavoriteUseCaseImpl = FakeInsertFavoriteUseCaseImpl()
    private val fakeDeleteFavoriteUseCaseImpl = FakeDeleteFavoriteUseCaseImpl()

    private val invalidSearchQueryValue = null
    private val validSearchQueryValue = "Not empty some query value"
    private val availableContentTypeValueList = mutableListOf(
        SeriesType,
        ComicType,
        EventType,
        CharacterType,
        CreatorType
    )

    @Before
    fun setUp() {
        val localDataSource = MarvelLocalDataSourceImpl(mockMarveDao)
        val pagingDataSource = MarvelRemotePagingDataSourceImpl(mockMarvelApi)
        val remoteDataSource = MarvelRemoteDataSourceImpl(mockMarvelApi)

        val repository = MarvelRepositoryImpl(localDataSource, remoteDataSource, pagingDataSource)

        val getSearchDataUseCase = GetSearchDataUseCaseImpl(repository)

        searchViewModel = SearchViewModel(
            getSearchDataUseCase,
            fakeGetFavoriteUseCaseImpl,
            fakeInsertFavoriteUseCaseImpl,
            fakeDeleteFavoriteUseCaseImpl
        )

        // PagingData 경우 Paging 에서 자체적으로 에러, 로딩상태 관리하므로
        // 리스너 등록하여 수신된 상태에 따라 뷰모델 errorSharedFlow, loadingStateFlow 를 조작한다.
        with(testPagingDataConsumer.testPagingDataDiffer) {
            addLoadStateListener { searchViewModel.pagingLoadStateListener.onLoad(it, itemCount) }
        }
    }

    @Test
    fun `getSearchPagingData()_메서드_호출_시_query_세팅_안된경우_이후_로직_미호출_테스트`() = runTest {
        // [Given] (queryStateFlow 초기값 null)
        searchViewModel.queryStateFlow.value = invalidSearchQueryValue
        searchViewModel.currentContentType.value = availableContentTypeValueList.random()

        // [When]
        searchViewModel.getSearchPagingData()
        doPagingDataConsume()

        // [Then]
        when (searchViewModel.currentContentType.value) {
            is SeriesType ->
                coVerify(exactly = 0) { mockMarvelApi.getSeriesList(any(), any(), any()) }
            is ComicType ->
                coVerify(exactly = 0) { mockMarvelApi.getComicList(any(), any(), any()) }
            is EventType ->
                coVerify(exactly = 0) { mockMarvelApi.getEventList(any(), any(), any()) }
            is CharacterType ->
                coVerify(exactly = 0) { mockMarvelApi.getCharacterList(any(), any(), any()) }
            is CreatorType ->
                coVerify(exactly = 0) { mockMarvelApi.getCreatorList(any(), any(), any()) }
        }
    }

    @Test
    fun `getSearchPagingData()_메서드_호출_시_query_세팅된_경우_이후_로직_정상_호출_테스트`() = runTest {
        // [Given] (queryStateFlow 초기값 null)
        searchViewModel.queryStateFlow.value = validSearchQueryValue
        searchViewModel.currentContentType.value = availableContentTypeValueList.random()

        when (searchViewModel.currentContentType.value) {
            is SeriesType -> coEvery { mockMarvelApi.getSeriesList(any(), any(), any()) } returns mockk()
            is ComicType -> coEvery { mockMarvelApi.getComicList(any(), any(), any()) } returns mockk()
            is EventType -> coEvery { mockMarvelApi.getEventList(any(), any(), any()) } returns mockk()
            is CharacterType -> coEvery { mockMarvelApi.getCharacterList(any(), any(), any()) } returns mockk()
            is CreatorType -> coEvery { mockMarvelApi.getCreatorList(any(), any(), any()) } returns mockk()
        }

        // [When]
        searchViewModel.getSearchPagingData()
        doPagingDataConsume()

        // [Then]
        when (searchViewModel.currentContentType.value) {
            is SeriesType ->
                coVerify(exactly = 1) { mockMarvelApi.getSeriesList(any(), any(), any()) }
            is ComicType ->
                coVerify(exactly = 1) { mockMarvelApi.getComicList(any(), any(), any()) }
            is EventType ->
                coVerify(exactly = 1) { mockMarvelApi.getEventList(any(), any(), any()) }
            is CharacterType ->
                coVerify(exactly = 1) { mockMarvelApi.getCharacterList(any(), any(), any()) }
            is CreatorType ->
                coVerify(exactly = 1) { mockMarvelApi.getCreatorList(any(), any(), any()) }
        }
    }

    @Test
    fun `getSearchPagingData()_메서드_호출_시_searchUiDataStateFlow_정상_변화_테스트`() = runTest {
        searchViewModel.searchUiDataStateFlow.test {
            // [Given]
            searchViewModel.queryStateFlow.value = validSearchQueryValue
            searchViewModel.currentContentType.value = availableContentTypeValueList.random()

            // [When]
            searchViewModel.getSearchPagingData()

            // [Then]
            assertEquals(true, awaitItem() is SearchUiModel.Initialize)
            assertEquals(true, awaitItem() is SearchUiModel.Clear)
            assertEquals(true, awaitItem() is SearchUiModel.ShowData)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getSearchPagingData()_이미_요청한_파라미터로_중복_요청_방지_테스트`() = runTest {
        // [Given]
        searchViewModel.queryStateFlow.value = "검색어_1"
        searchViewModel.currentContentType.value = CharacterType
        searchViewModel.getSearchPagingData() // [검색어_1, CharacterType] 조합으로 검색 요청 (결과 : 요청 진행)
        doPagingDataConsume()

        // [When]
        searchViewModel.getSearchPagingData() // [검색어_1, CharacterType] 조합으로 중복 검색 요청 (결과 : 요청 미진행)
        doPagingDataConsume()

        // [Then]
        coVerify(exactly = 1) { mockMarvelApi.getCharacterList(any(), any(), any()) }
    }

    @Test
    fun `getSearchPagingData()_메서드_호출_후_에러_발생_시_SearchUiEvent_정상_변화_테스트`() = runTest {
        searchViewModel.searchUiEventSharedFlow.test {
            // [Given]
            searchViewModel.queryStateFlow.value = validSearchQueryValue
            searchViewModel.currentContentType.value = availableContentTypeValueList.random()
            val someError = Exception("에러 발생")
            val expectedItem = SearchUiEvent.ShowBadResult(ResultErrorType)

            when (searchViewModel.currentContentType.value) {
                is SeriesType -> coEvery { mockMarvelApi.getSeriesList(any(), any(), any())} throws someError
                is ComicType -> coEvery { mockMarvelApi.getComicList(any(), any(), any())} throws someError
                is EventType -> coEvery { mockMarvelApi.getEventList(any(), any(), any())} throws someError
                is CharacterType -> coEvery { mockMarvelApi.getCharacterList(any(), any(), any())} throws someError
                is CreatorType -> coEvery { mockMarvelApi.getCreatorList(any(), any(), any())} throws someError
            }

            // [When]
            searchViewModel.getSearchPagingData()
            doPagingDataConsume()

            // [Then]
            val actualItem = expectMostRecentItem()
            assertEquals(expectedItem, actualItem as SearchUiEvent.ShowBadResult)
            assertEquals(expectedItem.badResultType, actualItem.badResultType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getSearchPagingData()_메서드_호출_후_Empty_수신_시_SearchUiEvent_정상_변화_테스트`() = runTest {
        searchViewModel.searchUiEventSharedFlow.test {
            // [Given]
            searchViewModel.queryStateFlow.value = validSearchQueryValue
            searchViewModel.currentContentType.value = availableContentTypeValueList.random()
            val expectedItem = SearchUiEvent.ShowBadResult(ResultEmptyType)

            when (searchViewModel.currentContentType.value) {
                is SeriesType -> coEvery { mockMarvelApi.getSeriesList(any(), any(), any()) } returns generateEmptySeriesDto()
                is ComicType -> coEvery { mockMarvelApi.getComicList(any(), any(), any())} returns generateEmptyComicDto()
                is EventType -> coEvery { mockMarvelApi.getEventList(any(), any(), any())} returns generateEmptyEventDto()
                is CharacterType -> coEvery { mockMarvelApi.getCharacterList(any(), any(), any())} returns generateEmptyCharactersDto()
                is CreatorType -> coEvery { mockMarvelApi.getCreatorList(any(), any(), any())} returns generateEmptyCreatorDto()
            }

            // [When]
            searchViewModel.getSearchPagingData()
            doPagingDataConsume()

            // [Then]
            val actualItem = expectMostRecentItem()
            assertEquals(expectedItem, actualItem as SearchUiEvent.ShowBadResult)
            assertEquals(expectedItem.badResultType, actualItem.badResultType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `setSearchUiEventSharedFlow()_메서드_호출_searchUiEventSharedFlow_변화_테스트`() = runTest {
        searchViewModel.searchPageMoveEventSharedFlow.test {
            // [Given]
            val expectedContentId = Random.nextInt(Int.MAX_VALUE)
            val expectedContentType = availableContentTypeValueList.random()
            val expectedUiEvent = SearchPageMoveEvent.MoveToDetail(expectedContentId, expectedContentType)

            // [When]
            searchViewModel.setSearchUiEventSharedFlow(expectedUiEvent)

            // [Then]
            val actualUiEvent = awaitItem()
            assertEquals(expectedUiEvent, actualUiEvent as SearchPageMoveEvent.MoveToDetail)
            assertEquals(expectedContentId, actualUiEvent.contentId)
            assertEquals(expectedContentType,actualUiEvent.contentType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getContentTypeFromTabTitle()_메서드_호출_후_ContentType_반환_확인_테스트`() {
        // [Given]
        val expectedSeriesPair : Pair<String, ContentType?> = "Series" to SeriesType
        val expectedComicPair : Pair<String, ContentType?> = "Comic" to ComicType
        val expectedEventPair : Pair<String, ContentType?> = "Event" to EventType
        val expectedCharacterPair : Pair<String, ContentType?> = "Character" to CharacterType
        val expectedCreatorPair : Pair<String, ContentType?> = "Creator" to CreatorType
        val expectedInvalidPair : Pair<String, ContentType?> = "?" to null

        // [When]
        val actualTypeBySeries = searchViewModel.getContentTypeFromTabTitle(expectedSeriesPair.first)
        val actualTypeByComic = searchViewModel.getContentTypeFromTabTitle(expectedComicPair.first)
        val actualTypeByEvent = searchViewModel.getContentTypeFromTabTitle(expectedEventPair.first)
        val actualTypeByCharacter = searchViewModel.getContentTypeFromTabTitle(expectedCharacterPair.first)
        val actualTypeByCreator = searchViewModel.getContentTypeFromTabTitle(expectedCreatorPair.first)
        val actualTypeByNull = searchViewModel.getContentTypeFromTabTitle(expectedInvalidPair.first)

        // [Then]
        assertEquals(expectedSeriesPair.second, actualTypeBySeries)
        assertEquals(expectedComicPair.second, actualTypeByComic)
        assertEquals(expectedEventPair.second, actualTypeByEvent)
        assertEquals(expectedCharacterPair.second, actualTypeByCharacter)
        assertEquals(expectedCreatorPair.second, actualTypeByCreator)
        assertEquals(expectedInvalidPair.second, actualTypeByNull)
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_true_전달_시_insert_성공_후_favoriteIdListStateFlow_변화_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeValueList.random()
        val targetSearchResult = FakeSearchResultGenerator.generate(someContentId)
        val expectedFavoriteId = targetSearchResult.id
        val expectedContainFavoriteList = true

        // [Given] Pre-Situation (ContentType 설정)
        searchViewModel.currentContentType.value = someContentType // changeFavoriteStatus() 호출 시 currentContentType 참조

        // [When]
        searchViewModel.changeFavoriteStatus(targetSearchResult, true)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetSearchResult.parseFavorite(someContentType)))

        // [Then]
        val favoriteIdList = searchViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, favoriteIdList.contains(expectedFavoriteId))
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_true_전달_시_insert_실패_후_에러_메세지_수신_테스트`() = runTest {
        searchViewModel.messageSharedFlow.test {
            // [Given] Value
            val someContentId = 0
            val someContentType = availableContentTypeValueList.random()
            val targetSearchResult = FakeSearchResultGenerator.generate(someContentId)
            val expectedException = Exception("에러 발생")

            // [Given] Pre-Situation (ContentType 설정)
            searchViewModel.currentContentType.value = someContentType // changeFavoriteStatus() 호출 시 currentContentType 참조

            // [When]
            searchViewModel.changeFavoriteStatus(targetSearchResult, true)
            fakeInsertFavoriteUseCaseImpl.emit(Result.failure(expectedException))

            // [Then]
            val actualMessage = awaitItem()
            assertEquals(expectedException.message, actualMessage)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_false_전달_시_delete_성공_후_favoriteIdListStateFlow_변화_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeValueList.random()
        val targetSearchResult = FakeSearchResultGenerator.generate(someContentId)
        val expectedFavoriteId = targetSearchResult.id
        val expectedContainFavoriteList = false

        // [Given] Pre-Situation (ContentType 설정, 즐겨찾기 추가)
        searchViewModel.currentContentType.value = someContentType // changeFavoriteStatus() 호출 시 currentContentType 참조
        searchViewModel.changeFavoriteStatus(targetSearchResult, true)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetSearchResult.parseFavorite(someContentType)))

        // [When]
        searchViewModel.changeFavoriteStatus(targetSearchResult, false)
        fakeDeleteFavoriteUseCaseImpl.emit(Result.success(targetSearchResult.parseFavorite(someContentType)))

        // [Then]
        val favoriteIdList = searchViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, favoriteIdList.contains(expectedFavoriteId))
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_false_전달_시_delete_실패_후_에러_메세지_수신_테스트`() = runTest {
        searchViewModel.messageSharedFlow.test {
            // [Given] Value
            val someContentId = 0
            val someContentType = availableContentTypeValueList.random()
            val targetSearchResult = FakeSearchResultGenerator.generate(someContentId)
            val expectedException = Exception("에러 발생")
            val expectedRemainFavoriteId = true

            // [Given] Pre-Situation (ContentType 설정, 즐겨찾기 추가)
            searchViewModel.currentContentType.value = someContentType // changeFavoriteStatus() 호출 시 currentContentType 참조
            searchViewModel.changeFavoriteStatus(targetSearchResult, true)
            fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetSearchResult.parseFavorite(someContentType)))

            // [When]
            searchViewModel.changeFavoriteStatus(targetSearchResult, false)
            fakeInsertFavoriteUseCaseImpl.emit(Result.failure(expectedException))

            // [Then]
            val actualMessage = awaitItem()
            val actualFavoriteIdList = searchViewModel.favoriteIdListStateFlow.value
            assertEquals(expectedException.message, actualMessage)
            assertEquals(expectedRemainFavoriteId, actualFavoriteIdList.contains(someContentId))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getFavoriteList()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeValueList.random()
        val targetSearchResult = FakeSearchResultGenerator.generate(someContentId)
        val targetFavorite = targetSearchResult.parseFavorite(someContentType)
        val expectedFavoriteId = targetSearchResult.id
        val expectedContainFavoriteList = true

        // [Given] Pre-Situation (ContentType 설정, 즐겨찾기 추가)
        searchViewModel.currentContentType.value = someContentType // getFavoriteList() 호출 시 currentContentType 참조
        searchViewModel.changeFavoriteStatus(targetSearchResult, true)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetFavorite))

        // [When]
        searchViewModel.getFavoriteList()
        fakeGetFavoriteUseCaseImpl.emit(Result.success(mutableListOf(targetFavorite)))

        // [Then]
        val actualFavoriteIdList = searchViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, actualFavoriteIdList.contains(expectedFavoriteId))
    }

    /**
     * PagingData 내부에 Cold Stream 인 Flow Consume 처리
     *
     * - 직접적인 dataStateFlow 의 상태를 테스트하는 경우가 아닌, 단순 소비가 필요한 경우 사용되는 메서드.
     *
     * - Cold Stream 은 Consume 하기 전까지 데이터를 발행하지 않으므로 Paging 테스트 시
     *   클라이언트 엔드포인트인 Service(UserService.kt) 까지 정상적으로 호출이 되기 위해선 수신받은 Flow 를 Consume 처리 해주어야 동작
     * */
    private suspend fun doPagingDataConsume() = searchViewModel.searchUiDataStateFlow.test {
        val item = expectMostRecentItem()

        if (item is SearchUiModel.ShowData) {
            testPagingDataConsumer.submitPagingDataToDiffer(item.data)
        }

        cancelAndConsumeRemainingEvents()
    }
}

