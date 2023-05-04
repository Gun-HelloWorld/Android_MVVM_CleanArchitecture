package com.gun.android_marvel_example.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class TestData(val title: String, val imgUrl: String): Parcelable