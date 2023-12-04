package com.lelestacia.hayate.feature.anime.shared.model.title

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeTitle(
    val type: String,
    val title: String
) : Parcelable
