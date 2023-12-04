package com.lelestacia.hayate.feature.anime.shared.model.image

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeWebp(
    val imageUrl: String,
    val smallImageUrl: String,
    val largeImageUrl: String
) : Parcelable
