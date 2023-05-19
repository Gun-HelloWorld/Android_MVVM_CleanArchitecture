package com.gun.data.entity.character

import android.text.TextUtils

data class Result(
    val comics: Comics,
    val description: String?,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
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