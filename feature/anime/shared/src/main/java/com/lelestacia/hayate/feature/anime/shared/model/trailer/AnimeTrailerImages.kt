package com.lelestacia.hayate.feature.anime.shared.model.trailer

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeTrailerImages(
    val imageUrl: String?,
    val smallImageUrl: String?,
    val mediumImageUrl: String?,
    val largeImageUrl: String?,
    val maximumImageUrl: String?
) : Parcelable
