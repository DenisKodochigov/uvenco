package com.example.uvenco.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.uvenco.R

/*** Contract for information needed on every App navigation destination*/
interface ScreenDestination {
    val route: String
    val routeWithArgs: String
    val nameScreen: Int
    val icon: ImageVector
    val iconText: Int
    val pictureDay: Int
    val pictureNight: Int
    val showFab: Boolean
    var textFABId: Int
    var onClickFAB: () -> Unit
}
/*** App app navigation destinations*/
object CatalogDestination : ScreenDestination {
    override val route = "catalog"
    override val routeWithArgs = route
    override val nameScreen = R.string.screen_name_1
    override val icon = Icons.Filled.AccountBox
    override val iconText = 0
    override val pictureDay = 0
    override val pictureNight = 0
    override val showFab: Boolean = true
    override var textFABId = 0
    override var onClickFAB: () -> Unit = {}
}
object ConfigDestination : ScreenDestination {
    override val route = "config"
    override val nameScreen = R.string.screen_name_2
    override val icon = Icons.Default.Build
    override val iconText = 0
    override val pictureDay = R.drawable.ic_launcher_background
    override val pictureNight = R.drawable.ic_launcher_background
    override val showFab: Boolean = false
    override var textFABId = 0
    override var onClickFAB: () -> Unit = {}

    const val ARG = "arg_id"
    override val routeWithArgs = "${route}/{$ARG}"
    val arguments = listOf(navArgument(ARG) { type = NavType.IntType })
}

val navBottomScreens = listOf(CatalogDestination, ConfigDestination)
val listScreens = listOf(CatalogDestination, ConfigDestination)



