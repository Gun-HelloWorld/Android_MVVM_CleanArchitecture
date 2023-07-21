
package com.gun.presentation.ui.detail

import app.cash.turbine.test
import com.gun.domain.common.*
import com.gun.domain.usecase.DeleteUseCase
import com.gun.domain.usecase.GetUseCase
import com.gun.domain.usecase.InsertUseCase
import com.gun.presentation.MainDispatcherRule
import com.gun.presentation.fake.data.FakeContentDetailGenerator
import com.gun.presentation.fake.usecase.FakeGetDetailDataUseCaseImpl
import io.mockk.mockk
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
    private val mockGetFavoriteUseCase = mockk<GetUseCase.GetFavoriteUseCase>()
    private val mockInsertFavoriteUseCase = mockk<InsertUseCase.InsertFavoriteUseCase>()
    private val mockDeleteFavoriteUseCase = mockk<DeleteUseCase.DeleteFavoriteUseCase>()

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            fakeGetDetailDataUseCaseImpl,
            mockGetFavoriteUseCase,
            mockInsertFavoriteUseCase,
            mockDeleteFavoriteUseCase
        )
    }

    @Test
    fun `getDetailData()_메서드_호출_시_데이터_정상_수신_테스트`() = runTest {
        // Given
        val validContentId = 1
        val someContentType = availableContentTypeList.random()
        val expectedContentDetail = FakeContentDetailGenerator.generate(validContentId, someContentType)

        // When
        detailViewModel.getDetailData(validContentId, CharacterType)
        fakeGetDetailDataUseCaseImpl.emit(Result.success(expectedContentDetail))
        val actualValue = detailViewModel.detailUiStateFlow.value

        // Then
        assertEquals(false , actualValue is DetailUiModelState.Nothing)
        assertEquals(true , actualValue is DetailUiModelState.ShowData)
        assertEquals(expectedContentDetail, (actualValue as DetailUiModelState.ShowData).data)
    }

    @Test
    fun `getDetailData()_메서드_호출_시_잘못된_파라미터_전달_후_에러_메세지_수신_테스트`() = runTest {
        detailViewModel.messageSharedFlow.test {
            // Given
            val invalidContentId = -1
            val someContentType = availableContentTypeList.random()
            val expectedMessage = "Error"

            // When
            detailViewModel.getDetailData(invalidContentId, someContentType)
            fakeGetDetailDataUseCaseImpl.emit(Result.failure(Throwable(expectedMessage)))

            // then
            assertEquals(expectedMessage, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}