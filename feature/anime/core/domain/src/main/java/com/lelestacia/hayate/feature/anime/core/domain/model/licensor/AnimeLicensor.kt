package com.lelestacia.hayate.feature.anime.core.domain.model.licensor

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeLicensor(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
) : Parcelable
