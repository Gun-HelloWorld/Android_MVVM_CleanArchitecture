package com.gun.presentation.test

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

class TestPagingDataConsumer<P : Any> {

    /**
     * PagingData 는 값을 추출할 수 없으므로, PagingData 를 RecyclerView 에 매핑하는 헬퍼 클래스 사용
     * */
    val testPagingDataDiffer = AsyncPagingDataDiffer<P>(
        diffCallback = TestDiffCallback(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Main,
    )

    /**
     * `PagingData`를 `PagingDataDiffer`에 Submit
     *
     * - PagingDataAdapter(`Paging`용 RecyclerViewAdapter) 에 데이터를 전달하는 것과 같은 역할을 하며
     *   PagingDiffer 로 데이터 전달 시, 성공/실패 데이터에 따라 userViewModel.loadStateListener 로 상태 수신되어 에러, 로딩 상태를 업데이트 한다.
     *
     * - 직접적으로 확인할 수 없는 PagingData 를 `PagingDataDiffer`에 데이터 Submit 후,
     *   PagingDataDiffer.snapshot() 호출을 통해 세부 데이터를 확인 할 수 있다.
     * */
    fun submitPagingDataToDiffer(pagingData: PagingData<P>) = runTest {
        val job = launch {
            testPagingDataDiffer.submitData(pagingData)
        }

        // 대기열에 남은 항목이 없을 때까지 스케줄러에서 다른 코루틴을 모두 실행
        advanceUntilIdle()

        job.cancel()
    }

}
