package com.lelestacia.hayate.feature.anime.shared.model.aired

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnimeProp(
    val from: AnimePropFrom,
    val to: AnimePropTo
) : Parcelable