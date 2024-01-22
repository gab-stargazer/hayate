package com.lelestacia.hayate.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.lelestacia.hayate.common.shared.event.HayateNavigationType

@Composable
fun HandleNavigation(
    navController: NavHostController,
    navigation: HayateNavigationType?,
    postNavigate: () -> Unit,
) {
    LaunchedEffect(key1 = navigation) {
        navigation?.let {
            when (navigation) {
                is HayateNavigationType.Navigate -> navController.navigate(
                    route = navigation.route,
                    navOptions = navigation.options
                )

                HayateNavigationType.PopBackstack -> navController.popBackStack()
            }
            postNavigate()
        }
    }
}