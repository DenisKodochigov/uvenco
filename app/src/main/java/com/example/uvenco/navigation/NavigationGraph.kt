package com.example.uvenco.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.uvenco.entity.Const.DEFAULT_SCREEN
import com.example.uvenco.entity.Const.DELAY_SCREEN
import com.example.uvenco.entity.Const.DURATION_SCREEN
import com.example.uvenco.ui.screens.catalog.CatalogScreen
import com.example.uvenco.ui.screens.config.ConfigScreen

fun NavGraphBuilder.catalog( goToScreenConfig: (Int) -> Unit,
) {
    template(
        routeTo = CatalogDestination.route,
        content = {
            CatalogScreen(onClickItem = { goToScreenConfig(it) },)
        }
    )
}
fun NavGraphBuilder.config() {
    template(
        routeTo = ConfigDestination.routeWithArgs,
        argument = ConfigDestination.arguments,
        content = { navBackStackEntry ->
            ConfigScreen(id = navBackStackEntry.arguments?.getInt(ConfigDestination.ARG) ?: 0)
        }
    )
}


fun NavGraphBuilder.template(
    routeTo: String,
    argument: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = routeTo,
        arguments = argument,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
//        popEnterTransition = popEnterTransition,
//        popExitTransition = popExitTransition,
        content = content
    )
}

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    val targetScreen = targetState.destination.route ?: DEFAULT_SCREEN.route
    val direction: Double = if (targetScreen == DEFAULT_SCREEN.route) -1.0 else 1.0
    slideInHorizontally(animationSpec =tweenM(), initialOffsetX = { (it * direction).toInt() }) +
        fadeIn( animationSpec = tweenM() )
}
val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    val targetScreen = targetState.destination.route ?: DEFAULT_SCREEN.route
    val direction: Double = if (targetScreen == DEFAULT_SCREEN.route) 1.0 else -1.0
    slideOutHorizontally(animationSpec = tweenM(), targetOffsetX = { (it * direction).toInt() }) +
    fadeOut(animationSpec = tweenM())
}
val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    val targetScreen = targetState.destination.route ?: DEFAULT_SCREEN.route
    val direction: Double = if (targetScreen == DEFAULT_SCREEN.route) (1/3.0) else (1/3.0)
    slideInHorizontally(initialOffsetX = { (it * direction).toInt() }, animationSpec = tweenM())
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    val targetScreen = targetState.destination.route ?: DEFAULT_SCREEN.route
    val direction: Double = if (targetScreen == DEFAULT_SCREEN.route) (1/3.0) else (1/3.0)
    slideOutHorizontally(targetOffsetX = { (it * direction).toInt() }, animationSpec = tweenM())
}
fun <T>tweenM(): TweenSpec<T> =
    tween( durationMillis = DURATION_SCREEN, delayMillis = DELAY_SCREEN, easing = LinearOutSlowInEasing)

