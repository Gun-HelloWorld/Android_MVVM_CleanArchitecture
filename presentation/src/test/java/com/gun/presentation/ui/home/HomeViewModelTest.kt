package com.gun.presentation.ui.home

import app.cash.turbine.test
import com.gun.domain.model.home.HomeList
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.*
import com.gun.presentation.fake.usecase.FakeGetHomeDataUseCaseImpl
import com.gun.presentation.ui.home.model.mapper.toUiModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fakeGetHomeDataUseCaseImpl: FakeGetHomeDataUseCaseImpl

    @Before
    fun setUp() {
        fakeGetHomeDataUseCaseImpl = FakeGetHomeDataUseCaseImpl()
        homeViewModel = HomeViewModel(fakeGetHomeDataUseCaseImpl)
    }

    @Test
    fun `getHomeListData()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // Given
        val offset = 0
        val someLimit = (1..30).random()
        val fakeHomeList = FakeHomeListGenerator.generate(offset, someLimit)
        val expectedHomeUiModel = fakeHomeList.toUiModel()

        // When
        homeViewModel.getHomeListData(offset, someLimit)
        fakeGetHomeDataUseCaseImpl.emit(Result.success(fakeHomeList))
        val actualValue = homeViewModel.homeUiStateFlow.value

        // Then
        assertEquals(true , actualValue is HomeUiModelState.ShowData)
        assertEquals(expectedHomeUiModel, (actualValue as HomeUiModelState.ShowData).data)
    }

    @Test
    fun `getHomeListData()_메서드_잘못된_파라미터_전달_시_에러_메세지_수신_테스트`() = runTest {
        homeViewModel.messageSharedFlow.test {
            // Given
            val offset = 0
            val invalidLimit = -1
            val expectedMessage = "Error"

            // When
            homeViewModel.getHomeListData(offset, invalidLimit)
            fakeGetHomeDataUseCaseImpl.emit(Result.failure(Throwable(expectedMessage)))

            // Then
            assertEquals(expectedMessage, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // Given
        val offset = 0
        val someLimit = (6..30).random()
        val fakeHomeUiModel = FakeHomeListGenerator.generate(offset, someLimit).toUiModel()
        val expectedHomeBannerCount = 0..HOME_BANNER_COUNT

        // When
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(fakeHomeUiModel)?.size

        // Then
        assertNotNull(actualHomeBannerCount)
        assertEquals(true, actualHomeBannerCount in expectedHomeBannerCount)
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_반환된_List_사이즈_5_미만_이어도_해당_갯수_만큼_정상_수신_테스트`() = runTest {
        // Given
        val offset = 0
        val limit = 5
        val expectedHomeBannerCount = 4
        val fakeHomeList = FakeHomeListGenerator.generate(offset, expectedHomeBannerCount)

        // When
        homeViewModel.getHomeListData(offset, limit)
        fakeGetHomeDataUseCaseImpl.emit(Result.success(fakeHomeList))
        val homeUiModel = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(homeUiModel)?.size

        // Then
        assertEquals(expectedHomeBannerCount, actualHomeBannerCount)
    }

    @Test
    fun `getFilterHomeBannerModel()_메서드_호출_시_썸네일_유효하지_않은_경우_수신_테스트`() = runTest {
        // Given
        val offset = 0
        val limit = 3
        val expectedHomeBannerCount = 0
        val invalidThumbnailList = FakeEventGenerator.generateInvalidThumbnailList()
        val fakeHomeList = HomeList(null, null, null, eventList = invalidThumbnailList, null)

        // When
        homeViewModel.getHomeListData(offset, limit)
        fakeGetHomeDataUseCaseImpl.emit(Result.success(fakeHomeList))
        val homeUiModel = (homeViewModel.homeUiStateFlow.value as HomeUiModelState.ShowData).data
        val actualHomeBannerCount = homeViewModel.getFilterHomeBannerModel(homeUiModel)?.size

        // then
        assertEquals(expectedHomeBannerCount, actualHomeBannerCount)
    }
}