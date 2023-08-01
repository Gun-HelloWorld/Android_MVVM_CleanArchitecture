package com.gun.presentation.fake.data

import com.gun.domain.model.search.SearchResult

object FakeSearchResultGenerator {

    fun generate(id: Int): SearchResult {
        return SearchResult(
            id = id,
            name = "name$id",
            thumbnailPath = "thumbnailPath$id",
            thumbnailExtension = "thumbnailExtension$id",
            modified = "modified$id"
        )
    }

}