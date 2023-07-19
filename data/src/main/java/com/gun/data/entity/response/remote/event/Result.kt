package com.gun.data.entity.response.remote.event

import android.text.TextUtils

data class Result(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String?,
    val end: String?,
    val id: Int,
    val modified: String,
    val next: Next,
    val previous: Previous,
    val resourceURI: String,
    val series: Series,
    val start: String?,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
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