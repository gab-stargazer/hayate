package com.lelestacia.hayate.feature.anime.core.domain.model.genre

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeGenre(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
) : Parcelable
