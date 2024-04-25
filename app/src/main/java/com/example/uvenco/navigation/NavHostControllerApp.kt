package com.example.uvenco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uvenco.entity.Const.DEFAULT_SCREEN

fun NavHostController.navigateToScreen(route: String) = this.navigate(route) { launchSingleTop = true }
fun NavHostController.navigateToConfig(id: Int) {
    this.navigateToScreen("${ConfigDestination.route}/$id")
}

