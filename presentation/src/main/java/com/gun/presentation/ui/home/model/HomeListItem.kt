package com.gun.presentation.ui.home.model

import android.os.Parcelable
import com.gun.domain.common.Constants.TYPE_CHARACTER
import com.gun.domain.common.Constants.TYPE_COMIC
import com.gun.domain.common.Constants.TYPE_CREATOR
import com.gun.domain.common.Constants.TYPE_EVENT
import com.gun.domain.common.Constants.TYPE_SERIES
import kotlinx.parcelize.Parcelize

private const val HOME_LIST_ITEM_IMAGE_SIZE = "portrait_xlarge"
private const val HOME_BANNER_ITEM_IMAGE_SIZE = "landscape_xlarge"

@Parcelize
data class HomeListItem (
    val id: Int,
    val name: String,
    private val thumbnailPath: String,
    private val thumbnailExtension: String,
    private val type: String,
    ) : Parcelable {

    // Parcelize 에서 sealed 클래스 미지원으로 속성을 감추고 메서드로 대체
    fun getHomeUiModelType(): HomeUiModelType {
        return when(type) {
            TYPE_CHARACTER -> CharacterType
            TYPE_COMIC -> ComicType
            TYPE_CREATOR -> CreatorType
            TYPE_EVENT -> EventType
            TYPE_SERIES -> SeriesType
            else -> throw IllegalStateException("InvalidType. type : $type")
        }
    }

    fun getListItemThumbnailUrl(): String {
        return "${thumbnailPath}/${HOME_LIST_ITEM_IMAGE_SIZE}.${thumbnailExtension}"
    }

    fun getBannerItemThumbnailUrl(): String {
        return "${thumbnailPath}/${HOME_BANNER_ITEM_IMAGE_SIZE}.${thumbnailExtension}"
    }

    fun isThumbnailAvailable(): Boolean {
        return !thumbnailPath.isNullOrEmpty() &&
                !thumbnailPath.contains("image_not_available") &&
                !thumbnailExtension.isNullOrEmpty()
    }

    companion object {
        fun thumbnailAvailableComparator() = Comparator<HomeListItem> { a, b ->
            a.isThumbnailAvailable().not().compareTo(b.isThumbnailAvailable().not())
        }
    }
}
