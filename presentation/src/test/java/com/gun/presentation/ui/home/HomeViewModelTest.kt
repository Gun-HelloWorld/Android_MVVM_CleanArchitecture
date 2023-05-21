package com.gun.presentation.ui.home

import app.cash.turbine.test
import com.gun.domain.model.Event
import com.gun.domain.model.home.HomeList
import com.gun.presentation.ui.home.model.mapper.toUiModel
import com.gun.presentation.ui.home.test.MainDispatcherRule
import com.gun.presentation.ui.home.test.fake.usecase.FakeGetHomeDataUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fakeGetHomeDataUseCase: FakeGetHomeDataUseCase

    @Before
    fun setUp() {
        fakeGetHomeDataUseCase = FakeGetHomeDataUseCase()
        homeViewModel = HomeViewModel(fakeGetHomeDataUseCase)
    }

    @Test
    fun `getHomeListData()_메서드_호출_시_데이터_정상_수신_테스트`() {
        // given
        val offset = HOME_LIST_OFFSET
        val limit = HOME_LIST_LIMIT
        val expectedHomeUiModelCount = 5 // [Character, Comic, Creator, Event, Series]
        val expectedHomeUiModel = HomeList(
            characterList = fakeGetHomeDataUseCase.generateFakeCharacterList(offset, limit),
            comicList = fakeGetHomeDataUseCase.generateFakeComicList(offset, limit),
            creatorList = fakeGetHomeDataUseCase.generateFakeCreatorList(offset, limit),
            eventList = fakeGetHomeDataUseCase.generateFakeEventList(offset, limit),
            seriesList = fakeGetHomeDataUseCase.generateFakeSeriesList(offset, limit)
        ).toUiModel()

        // when
        homeViewModel.getHomeListData(offset, limit)
        val actualData = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeUiModelCount = actualData.homeUiSubModelList.size

        // then
        assertEquals(expectedHomeUiModelCount, actualHomeUiModelCount)
        assertEquals(expectedHomeUiModel, actualData)
    }

    @Test
    fun `getHomeListData()_메서드_잘못된_파라미터_전달_시_에러_메세지_수신_테스트`() = runTest {
        // given
        val offset = 0
        val limit = 0

        homeViewModel.messageSharedFlow.test {
            // when
            homeViewModel.getHomeListData(offset, limit)

            // then
            assertEquals(awaitItem(), "Error")

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_데이터_정상_수신_테스트`() {
        // given
        val offset = HOME_LIST_OFFSET
        val limit = HOME_LIST_LIMIT
        val expectedHomeBannerCount = HOME_BANNER_COUNT
        fakeGetHomeDataUseCase.generateFakeEventList(offset, limit)

        // when
        homeViewModel.getHomeListData(offset, limit)
        val resultData = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(resultData)?.size

        // then
        assertEquals(expectedHomeBannerCount, actualHomeBannerCount)
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_파라미터_사이즈_5_미만_이어도_해당_갯수_만큼_정상_수신_테스트`() {
        // given
        val offset = HOME_LIST_OFFSET
        val limit = HOME_LIST_LIMIT
        val expectedHomeBannerCount = 4
        fakeGetHomeDataUseCase.generateFakeEventList(offset, expectedHomeBannerCount)

        // when
        homeViewModel.getHomeListData(offset, limit)
        val resultData = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(resultData)?.size

        // then
        assertEquals(expectedHomeBannerCount, actualHomeBannerCount)
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_썸네일_유효하지_않은_경우_수신_테스트`() {
        // given
        val offset = HOME_LIST_OFFSET
        val limit = HOME_LIST_LIMIT
        val expectedHomeBannerCount = 0
        val imageNotAvailableCase = Event(0, "", "", "", "", thumbnailPath = "image_not_available", thumbnailExtension = "정상확장자","", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        val thumbnailPathEmptyCase = Event(0, "", "", "", "", thumbnailPath = "", thumbnailExtension = "정상확장자", "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        val extensionEmptyCase = Event(0, "", "", "", "", thumbnailPath = "https://정상주소.com/", thumbnailExtension = "", "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        fakeGetHomeDataUseCase.fakeEventList = mutableListOf(imageNotAvailableCase,thumbnailPathEmptyCase,extensionEmptyCase)

        // when
        homeViewModel.getHomeListData(offset, limit)
        val resultData = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(resultData)?.size

        // then
        assertEquals(expectedHomeBannerCount, actualHomeBannerCount)
    }
}