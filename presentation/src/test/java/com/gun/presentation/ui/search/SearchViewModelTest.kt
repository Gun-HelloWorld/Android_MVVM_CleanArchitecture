package com.gun.presentation.ui.search

import androidx.paging.*
import app.cash.turbine.test
import com.gun.data.datasource.MarvelRemoteDataSourceImpl
import com.gun.data.datasource.MarvelRemotePagingDataSourceImpl
import com.gun.data.network.MarvelApi
import com.gun.data.repository.MarvelRepositoryImpl
import com.gun.domain.common.*
import com.gun.domain.model.search.SearchResult
import com.gun.domain.usecase.GetSearchDataUseCaseImpl
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyCharactersDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyComicDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyCreatorDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptyEventDto
import com.gun.presentation.fake.data.FakeDtoGenerator.generateEmptySeriesDto
import com.gun.presentation.test.TestPagingDataConsumer
import com.gun.presentation.ui.common.ResultEmptyType
import com.gun.presentation.ui.common.ResultErrorType
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert
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
        val pagingDataSource = MarvelRemotePagingDataSourceImpl(mockMarvelApi)
        val remoteDataSource = MarvelRemoteDataSourceImpl(mockMarvelApi)
        val repository = MarvelRepositoryImpl(remoteDataSource, pagingDataSource)
        val useCase = GetSearchDataUseCaseImpl(repository)
        searchViewModel = SearchViewModel(useCase)

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
            Assert.assertEquals(true, awaitItem() is SearchUiModel.Initialize)
            Assert.assertEquals(true, awaitItem() is SearchUiModel.Clear)
            Assert.assertEquals(true, awaitItem() is SearchUiModel.ShowData)

            cancelAndConsumeRemainingEvents()
        }
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
            Assert.assertEquals(expectedItem, actualItem as SearchUiEvent.ShowBadResult)
            Assert.assertEquals(expectedItem.badResultType, actualItem.badResultType)

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
            Assert.assertEquals(expectedItem, actualItem as SearchUiEvent.ShowBadResult)
            Assert.assertEquals(expectedItem.badResultType, actualItem.badResultType)

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
            Assert.assertEquals(expectedUiEvent, actualUiEvent as SearchPageMoveEvent.MoveToDetail)
            Assert.assertEquals(expectedContentId, actualUiEvent.contentId)
            Assert.assertEquals(expectedContentType,actualUiEvent.contentType)
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
        Assert.assertEquals(expectedSeriesPair.second, actualTypeBySeries)
        Assert.assertEquals(expectedComicPair.second, actualTypeByComic)
        Assert.assertEquals(expectedEventPair.second, actualTypeByEvent)
        Assert.assertEquals(expectedCharacterPair.second, actualTypeByCharacter)
        Assert.assertEquals(expectedCreatorPair.second, actualTypeByCreator)
        Assert.assertEquals(expectedInvalidPair.second, actualTypeByNull)
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

