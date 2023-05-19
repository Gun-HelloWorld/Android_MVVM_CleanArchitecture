package com.gun.data.entity.series

import android.text.TextUtils

data class Result(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String?,
    val endYear: Int,
    val events: Events,
    val id: Int,
    val modified: String,
    val next: Next,
    val previous: Any,
    val rating: String,
    val resourceURI: String,
    val startYear: Int,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val type: String,
    val urls: List<Url>
) {
    fun getDetailUrl(): String {
        var detailUrl = ""

        if (urls.isNullOrEmpty()) {
            return detailUrl
        }

        for (url in urls) {
            if (TextUtils.equals("detail", url.type)) {
                detailUrl = url.url
                break
            }
        }

        return detailUrl
    }
}