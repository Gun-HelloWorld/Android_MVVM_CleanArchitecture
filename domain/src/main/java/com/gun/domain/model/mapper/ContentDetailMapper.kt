package com.gun.domain.model.mapper

import com.gun.domain.model.*
import com.gun.domain.model.detail.ContentDetail

fun Character.toContentDetail(): ContentDetail {
    return ContentDetail (
        id = id,
        name = name,
        format = "",
        description = description,
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        detailUrl = detailUrl,
        copyright = copyright,
        attributionText = attributionText,
        characterInfoList = emptyList(),
        comicInfoList = comicInfoList,
        seriesInfoList = seriesInfoList,
        storyInfoList = storyInfoList,
        eventInfoList = eventInfoList,
        creatorInfoList = emptyList()
    )
}

fun Comic.toContentDetail(): ContentDetail {
    return ContentDetail (
        id = id,
        name = title,
        description = description,
        format = format,
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        detailUrl = detailUrl,
        copyright = copyright,
        attributionText = attributionText,
        characterInfoList = characterInfoList,
        comicInfoList = emptyList(),
        seriesInfoList = seriesInfoList,
        storyInfoList = storyInfoList,
        eventInfoList = eventInfoList,
        creatorInfoList = creatorInfoList
    )
}

fun Creator.toContentDetail(): ContentDetail {
    return ContentDetail (
        id = id,
        name = fullName,
        description = "",
        format = "",
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        detailUrl = detailUrl,
        copyright = copyright,
        attributionText = attributionText,
        characterInfoList = emptyList(),
        comicInfoList = comicInfoList,
        seriesInfoList = seriesInfoList,
        storyInfoList = storyInfoList,
        eventInfoList = eventInfoList,
        creatorInfoList = emptyList()
    )
}

fun Event.toContentDetail(): ContentDetail {
    return ContentDetail (
        id = id,
        name = title,
        description = description,
        format = "",
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        detailUrl = detailUrl,
        copyright = copyright,
        attributionText = attributionText,
        characterInfoList = characterInfoList,
        comicInfoList = comicInfoList,
        seriesInfoList = seriesInfoList,
        storyInfoList = storyInfoList,
        eventInfoList = emptyList(),
        creatorInfoList = creatorInfoList
    )
}

fun Series.toContentDetail(): ContentDetail {
    return ContentDetail (
        id = id,
        name = title,
        description = description,
        format = "",
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        detailUrl = detailUrl,
        copyright = copyright,
        attributionText = attributionText,
        characterInfoList = characterInfoList,
        comicInfoList = comicInfoList,
        seriesInfoList = emptyList(),
        storyInfoList = storyInfoList,
        eventInfoList = eventInfoList,
        creatorInfoList = creatorInfoList
    )
}