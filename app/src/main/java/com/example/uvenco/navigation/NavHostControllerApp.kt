package com.example.uvenco.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateToScreen(route: String) = this.navigate(route) { launchSingleTop = true }
fun NavHostController.navigateToConfig(id: Int) {
    this.navigateToScreen("${ConfigDestination.route}/$id")
}

