package com.lelestacia.hayate.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lelestacia.hayate.common.theme.quickSandFamily

@Composable
fun CustomBottomNavigation(
    isDarkTheme: Boolean,
    selectedRoute: String,
    onNavigationItemClicked: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        HayateNavigationItem.forEach { item ->
            NavigationBarItem(
                selected = selectedRoute == item.route,
                onClick = {
                    onNavigationItemClicked(item.route)
                },
                icon = {
                    AnimatedContent(
                        targetState = selectedRoute == item.route,
                        label = "Icon Animation"
                    ) { selected ->
                        when (selected) {
                            true -> {
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = stringResource(id = item.title)
                                )
                            }

                            false -> {
                                Icon(
                                    imageVector = item.unselectedIcon,
                                    contentDescription = stringResource(id = item.title)
                                )
                            }
                        }
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = quickSandFamily
                        )
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor =
                    when (isDarkTheme) {
                        true -> Color.White
                        false -> Color.White
                    },
                    indicatorColor =
                    when (isDarkTheme) {
                        true -> MaterialTheme.colorScheme.inversePrimary
                        false -> MaterialTheme.colorScheme.primary
                    },
                    selectedTextColor =
                    when (isDarkTheme) {
                        true -> MaterialTheme.colorScheme.onPrimaryContainer
                        false -> MaterialTheme.colorScheme.primary
                    }
                )
            )
        }
    }
}