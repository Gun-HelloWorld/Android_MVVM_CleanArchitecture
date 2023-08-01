package com.gun.presentation.ui.favorite

import app.cash.turbine.test
import com.gun.domain.common.*
import com.gun.domain.model.favorite.Favorite
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.FakeFavoriteGenerator
import com.gun.presentation.fake.usecase.FakeGetFavoriteDataUseCaseImpl
import com.gun.presentation.ui.common.ResultEmptyType
import com.gun.presentation.ui.common.ResultErrorType
import com.gun.presentation.ui.favorite.model.FavoriteUiEvent
import com.gun.presentation.ui.favorite.model.FavoriteUiFilterState
import com.gun.presentation.ui.favorite.model.FavoriteUiModelState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class FavoriteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

    private lateinit var fakeGetFavoriteUseCase: FakeGetFavoriteDataUseCaseImpl

    private val tempMaxQueryCount = 100

    private val availableSelectedFavoriteFilterList = mutableListOf(
        FavoriteUiFilterState.All,
        FavoriteUiFilterState.Series,
        FavoriteUiFilterState.Comic,
        FavoriteUiFilterState.Event,
        FavoriteUiFilterState.Character,
        FavoriteUiFilterState.Creator,
    )

    @Before
    fun setup() {
        fakeGetFavoriteUseCase = FakeGetFavoriteDataUseCaseImpl()

        favoriteViewModel = FavoriteViewModel(fakeGetFavoriteUseCase)
    }

    /**
     * 아래 테스트 코드에서 `FakeGetFavoriteDataUseCase`에서 사용되는 `Flow`는 `SharedFlow(Hot Flow)`이기 때문에,
     * `onCompletion()`을 통한 `HideLoading` 이벤트는 호출되지 않는다.
     * */
    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_이벤트_에러_발생_흐름_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList.random()

        favoriteViewModel.favoriteUiEventSharedFlow.test {
            // [Given]
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val responseDataList = emptyList<Favorite>()
            val expectedIsShowLoading = true
            val expectedIsShowBadResult = true
//            val expectedHideLoading = true

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(responseDataList))

            // [Then]
            val actualIsShowLoading = awaitItem() is FavoriteUiEvent.ShowLoading
            val actualIsShowBadResult = awaitItem() is FavoriteUiEvent.ShowBadResult
//            val actualIsHideLoading = awaitItem() is FavoriteUiEvent.HideLoading
            assertEquals(expectedIsShowLoading, actualIsShowLoading)
            assertEquals(expectedIsShowBadResult, actualIsShowBadResult)
//            assertEquals(expectedHideLoading, awaitItem() is FavoriteUiEvent.HideLoading)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_이벤트_ResultErrorType_타입_에러_발생_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList.random()

        favoriteViewModel.favoriteUiEventSharedFlow.test {
            // [Given]
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val someError = Exception("에러 발생")
            val expectedBadResultType = ResultErrorType

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.failure(someError))
            skipItems(1) // ShowLoading Skip

            // [Then]
            val actualBadResultType = (awaitItem() as FavoriteUiEvent.ShowBadResult).badResultType
            assertEquals(expectedBadResultType, actualBadResultType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_이벤트_ResultEmptyType_타입_에러_발생_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList.random()

        favoriteViewModel.favoriteUiEventSharedFlow.test {
            // [Given]
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val responseDataList = FakeFavoriteGenerator.generate(someContentType, 0)
            val expectedBadResultType = ResultEmptyType

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(responseDataList))
            skipItems(1) // ShowLoading Skip

            // [Then]
            val actualBadResultType = (awaitItem() as FavoriteUiEvent.ShowBadResult).badResultType
            assertEquals(expectedBadResultType, actualBadResultType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_데이터_정상_수신_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList.random()

        favoriteViewModel.favoriteUiStateFlow.test {
            // [Given]
            val someQueryCount = (1..tempMaxQueryCount)
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val responseDataList = FakeFavoriteGenerator.generate(someContentType, someQueryCount.random())
            val expectedIsShowData = true

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(responseDataList))

            // [Then]
            skipItems(1) // ShowLoading Skip
            val actualIsShowDataData = awaitItem() is FavoriteUiModelState.ShowData
            assertEquals(expectedIsShowData, actualIsShowDataData)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_데이터_동등성_검증_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList.random()

        favoriteViewModel.favoriteUiStateFlow.test {
            // [Given]
            val someQueryCount = (1..tempMaxQueryCount)
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val expectedFavoriteList = FakeFavoriteGenerator.generate(someContentType, someQueryCount.random())

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(expectedFavoriteList))
            skipItems(1) // ShowLoading Skip

            // [Then]
            val actualFavoriteList = (awaitItem() as FavoriteUiModelState.ShowData).data
            assertEquals(expectedFavoriteList, actualFavoriteList)

            cancelAndConsumeRemainingEvents()
        }
    }

    /**
     * `requestFavoriteList()` 호출 시 `contentType` 파라미터 `null` 전달 시 모든 `ContentType`에 대한 조회 수행
     * */
    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_데이터_ContentType_Null_Case_검증_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = FavoriteUiFilterState.All

        favoriteViewModel.favoriteUiStateFlow.test {
            // [Given]
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val someFavoriteList = FakeFavoriteGenerator.generateOnOfEachByFilterAll()
            val expectedSizeGroupByContentType = someFavoriteList.distinctBy { it.contentType }.size

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(someFavoriteList))
            skipItems(1) // ShowLoading Skip


            // [Then]
            val favoriteList = (awaitItem() as FavoriteUiModelState.ShowData).data
            val actualFavoriteListSizeByContentType = favoriteList.distinctBy { it.contentType }.size
            assertEquals(expectedSizeGroupByContentType, actualFavoriteListSizeByContentType)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestFavoriteList()_메서드_호출_후_UI_데이터_ContentType_NotNull_Case_검증_테스트`() = runTest {
        favoriteViewModel.selectedFavoriteFilter.value = availableSelectedFavoriteFilterList
            .filterNot{ it != FavoriteUiFilterState.All }
            .random()

        favoriteViewModel.favoriteUiStateFlow.test {
            // [Given]
            val someQueryCount = (1..tempMaxQueryCount)
            val someContentType = favoriteViewModel.selectedFavoriteFilter.value.parseToContentType()
            val someFavoriteList = FakeFavoriteGenerator.generate(someContentType, someQueryCount.random())
            val expectedValidSizeByContentType = someFavoriteList.filter { it.contentType == someContentType }.size

            // [When]
            favoriteViewModel.requestFavoriteList(someContentType)
            fakeGetFavoriteUseCase.emit(Result.success(someFavoriteList))
            skipItems(1) // ShowLoading Skip

            // [Then]
            val favoriteList = (awaitItem() as FavoriteUiModelState.ShowData).data
            val actualValidSizeByContentType = favoriteList.filter { it.contentType == someContentType }.size
            assertEquals(expectedValidSizeByContentType, actualValidSizeByContentType)

            cancelAndConsumeRemainingEvents()
        }
    }

}