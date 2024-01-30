package com.lelestacia.hayate.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun AnimatedButton(
    showCondition: Boolean,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    color: Color? = null,
    iconSize: Dp? = null,
) {
    AnimatedVisibility(
        visible = showCondition,
        enter = slideInHorizontally(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            initialOffsetX = { fullWidth -> fullWidth }
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            targetOffsetX = { fullWidth -> fullWidth }
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        IconButton(
            onClick = onClick,
            content = {
                if (color != null) {
                    Icon(
                        imageVector = icon,
                        tint = color,
                        contentDescription = contentDescription,
                        modifier =
                        when (iconSize != null) {
                            true -> Modifier.size(iconSize)
                            false -> Modifier
                        }
                    )
                } else {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        modifier =
                        when (iconSize != null) {
                            true -> Modifier.size(iconSize)
                            false -> Modifier
                        }
                    )
                }
            },
            modifier = modifier
        )
    }
}