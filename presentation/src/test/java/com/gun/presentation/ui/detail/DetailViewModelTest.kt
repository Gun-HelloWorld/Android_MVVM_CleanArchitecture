
package com.gun.presentation.ui.detail

import app.cash.turbine.test
import com.gun.domain.common.*
import com.gun.domain.model.mapper.parseFavorite
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.FakeContentDetailGenerator
import com.gun.presentation.fake.usecase.FakeDeleteFavoriteUseCaseImpl
import com.gun.presentation.fake.usecase.FakeGetDetailDataUseCaseImpl
import com.gun.presentation.fake.usecase.FakeGetFavoriteDataUseCaseImpl
import com.gun.presentation.fake.usecase.FakeInsertFavoriteUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("NonAsciiCharacters")
class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val availableContentTypeList = listOf(CharacterType, ComicType, SeriesType, EventType, CreatorType)

    private lateinit var detailViewModel: DetailViewModel

    private val fakeGetDetailDataUseCaseImpl = FakeGetDetailDataUseCaseImpl()
    private val fakeGetFavoriteUseCaseImpl = FakeGetFavoriteDataUseCaseImpl()
    private val fakeInsertFavoriteUseCaseImpl = FakeInsertFavoriteUseCaseImpl()
    private val fakeDeleteFavoriteUseCaseImpl = FakeDeleteFavoriteUseCaseImpl()

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            fakeGetDetailDataUseCaseImpl,
            fakeGetFavoriteUseCaseImpl,
            fakeInsertFavoriteUseCaseImpl,
            fakeDeleteFavoriteUseCaseImpl
        )
    }

    @Test
    fun `getDetailData()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // [Given]
        val validContentId = 1
        val someContentType = availableContentTypeList.random()
        val expectedContentDetail = FakeContentDetailGenerator.generate(validContentId, someContentType)

        // [When]
        detailViewModel.getDetailData(validContentId, someContentType)
        fakeGetDetailDataUseCaseImpl.emit(Result.success(expectedContentDetail))

        // [Then]
        val actualValue = detailViewModel.detailUiStateFlow.value
        assertEquals(false , actualValue is DetailUiModelState.Nothing)
        assertEquals(true , actualValue is DetailUiModelState.ShowData)
        assertEquals(expectedContentDetail, (actualValue as DetailUiModelState.ShowData).data)
    }

    @Test
    fun `getDetailData()_메서드_호출_시_잘못된_파라미터_전달_후_에러_메세지_수신_테스트`() = runTest {
        detailViewModel.messageSharedFlow.test {
            // [Given]
            val invalidContentId = -1
            val someContentType = availableContentTypeList.random()
            val expectedMessage = "Error"

            // [When]
            detailViewModel.getDetailData(invalidContentId, someContentType)
            fakeGetDetailDataUseCaseImpl.emit(Result.failure(Throwable(expectedMessage)))

            // [Then]
            assertEquals(expectedMessage, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_true_전달_시_insert_성공_후_favoriteIdListStateFlow_변화_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeList.random()
        val targetContentDetail = FakeContentDetailGenerator.generate(someContentId, someContentType)
        val expectedFavoriteId = targetContentDetail.id
        val expectedContainFavoriteList = true

        // [Given] Pre-Situation (상세조회)
        detailViewModel.getDetailData(someContentId, someContentType)
        fakeGetDetailDataUseCaseImpl.emit(Result.success(targetContentDetail))

        // [When]
        detailViewModel.changeFavoriteStatus(true, someContentType)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetContentDetail.parseFavorite(someContentType)))

        // [Then]
        val favoriteIdList = detailViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, favoriteIdList.contains(expectedFavoriteId))
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_true_전달_시_insert_실패_후_에러_메세지_수신_테스트`() = runTest {
        detailViewModel.messageSharedFlow.test {
            // [Given] Value
            val someContentId = 0
            val someContentType = availableContentTypeList.random()
            val targetContentDetail = FakeContentDetailGenerator.generate(someContentId, someContentType)
            val expectedException = Exception("에러 발생")
            val expectedRemainFavoriteId = false

            // [Given] Pre-Situation (상세조회)
            detailViewModel.getDetailData(someContentId, someContentType)
            fakeGetDetailDataUseCaseImpl.emit(Result.success(targetContentDetail))

            // [When]
            detailViewModel.changeFavoriteStatus(true, someContentType)
            fakeInsertFavoriteUseCaseImpl.emit(Result.failure(expectedException))

            // [Then]
            val actualMessage = awaitItem()
            val actualFavoriteIdList = detailViewModel.favoriteIdListStateFlow.value
            assertEquals(expectedException.message, actualMessage)
            assertEquals(expectedRemainFavoriteId, actualFavoriteIdList.contains(someContentId))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_false_전달_시_delete_성공_후_favoriteIdListStateFlow_변화_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeList.random()
        val targetContentDetail = FakeContentDetailGenerator.generate(someContentId, someContentType)
        val expectedFavoriteId = targetContentDetail.id
        val expectedContainFavoriteList = false

        // [Given] Pre-Situation (상세조회 & 즐겨찾기 추가)
        detailViewModel.getDetailData(someContentId, someContentType)
        fakeGetDetailDataUseCaseImpl.emit(Result.success(targetContentDetail))
        detailViewModel.changeFavoriteStatus(true, someContentType)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetContentDetail.parseFavorite(someContentType)))

        // [When]
        detailViewModel.changeFavoriteStatus(false, someContentType)
        fakeDeleteFavoriteUseCaseImpl.emit(Result.success(targetContentDetail.parseFavorite(someContentType)))

        // [Then]
        val favoriteIdList = detailViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, favoriteIdList.contains(expectedFavoriteId))
    }

    @Test
    fun `changeFavoriteStatus()_메서드_호출_시_isChecked_파라미터_false_전달_시_delete_실패_후_에러_메세지_수신_테스트`() = runTest {
        detailViewModel.messageSharedFlow.test {
            // [Given] Value
            val someContentId = 0
            val someContentType = availableContentTypeList.random()
            val targetContentDetail = FakeContentDetailGenerator.generate(someContentId, someContentType)
            val expectedException = Exception("에러 발생")
            val expectedRemainFavoriteId = true

            // [Given] Pre-Situation (상세조회 & 즐겨찾기 추가)
            detailViewModel.getDetailData(someContentId, someContentType)
            fakeGetDetailDataUseCaseImpl.emit(Result.success(targetContentDetail))
            detailViewModel.changeFavoriteStatus(true, someContentType)
            fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetContentDetail.parseFavorite(someContentType)))

            // [When]
            detailViewModel.changeFavoriteStatus(false, someContentType)
            fakeInsertFavoriteUseCaseImpl.emit(Result.failure(expectedException))

            // [Then]
            val actualMessage = awaitItem()
            val actualFavoriteIdList = detailViewModel.favoriteIdListStateFlow.value

            assertEquals(expectedException.message, actualMessage)
            assertEquals(expectedRemainFavoriteId, actualFavoriteIdList.contains(someContentId))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getFavoriteList()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // [Given] Value
        val someContentId = 0
        val someContentType = availableContentTypeList.random()
        val targetContentDetail = FakeContentDetailGenerator.generate(someContentId, someContentType)
        val targetFavorite = targetContentDetail.parseFavorite(someContentType)
        val expectedFavoriteId = targetContentDetail.id
        val expectedContainFavoriteList = true

        // [Given] Pre-Situation (상세조회 & 즐겨찾기 추가)
        detailViewModel.getDetailData(someContentId, someContentType)
        fakeGetDetailDataUseCaseImpl.emit(Result.success(targetContentDetail))
        detailViewModel.changeFavoriteStatus(true, someContentType)
        fakeInsertFavoriteUseCaseImpl.emit(Result.success(targetFavorite))

        // [When]
        detailViewModel.getFavoriteList(someContentType)
        fakeGetFavoriteUseCaseImpl.emit(Result.success(mutableListOf(targetFavorite)))

        // [Then]
        val actualFavoriteIdList = detailViewModel.favoriteIdListStateFlow.value
        assertEquals(expectedContainFavoriteList, actualFavoriteIdList.contains(expectedFavoriteId))
    }
}