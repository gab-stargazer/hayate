package com.lelestacia.hayate.feature.anime.core.domain.model.trailer

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeTrailer(
    val youtubeId: String?,
    val url: String?,
    val embedUrl: String?,
    val images: AnimeTrailerImages
) : Parcelable
