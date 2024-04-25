package com.example.uvenco.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.uvenco.entity.Const.DEFAULT_SCREEN

@Composable
fun NavHostApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = DEFAULT_SCREEN.route,
        modifier = modifier
    ){
        catalog( goToScreenConfig = { navController.navigateToConfig(it) })
        config ( onBaskScreen = { navController.popBackStack() })
    }
}
