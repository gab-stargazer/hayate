package com.lelestacia.hayate.feature.anime.core.domain.model.broadcast

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeBroadcast(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
) : Parcelable
