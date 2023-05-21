package com.gun.data.entity.comic

import android.text.TextUtils

data class Result(
    val characters: Characters,
    val collectedIssues: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val creators: Creators,
    val dates: List<Date>,
    val description: String?,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val events: Events,
    val format: String,
    val id: Int,
    val images: List<Thumbnail>,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<Price>,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val textObjects: List<TextObject>,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val urls: List<Url>,
    val variantDescription: String,
    val variants: List<ComicSummary>
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

    fun getAvailableDescription(): String {
        var desc = description ?: ""

        if (!desc.isNullOrEmpty()) {
            return desc
        }

        try {
            desc = textObjects.first { !it.text.isNullOrEmpty() }.text
        } catch (e: java.util.NoSuchElementException) {
            println("getAvailableDescription() - ${e.message}")
        }

        return desc
    }
}