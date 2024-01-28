package com.lelestacia.hayate.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp,
)

val LocalSpacing: ProvidableCompositionLocal<Spacing> = compositionLocalOf { Spacing() }

val spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
