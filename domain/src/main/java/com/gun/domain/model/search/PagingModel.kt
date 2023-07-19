package com.gun.domain.model.search

data class PagingModel<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    var list: List<T>?
)