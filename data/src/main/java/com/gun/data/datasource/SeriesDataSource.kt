package com.gun.data.datasource

import com.gun.data.entity.series.SeriesDto

class SeriesDataSource {
    interface Remote {
        suspend fun getSeries(page: Int, limit: Int): Result<SeriesDto>
    }
}