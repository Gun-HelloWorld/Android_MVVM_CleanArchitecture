package com.gun.data.entity.creator

import android.text.TextUtils

data class Result(
    val comics: Comics,
    val events: Events,
    val firstName: String,
    val fullName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val modified: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val suffix: String,
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