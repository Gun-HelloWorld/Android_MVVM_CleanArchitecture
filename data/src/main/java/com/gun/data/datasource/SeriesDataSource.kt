package com.gun.data.datasource

import com.gun.data.entity.series.SeriesDto

class SeriesDataSource {

    interface Remote {
        suspend fun getSeries(seriesId: Int): Result<SeriesDto>
        suspend fun getSeriesList(offset: Int, limit: Int): Result<SeriesDto>
    }

}