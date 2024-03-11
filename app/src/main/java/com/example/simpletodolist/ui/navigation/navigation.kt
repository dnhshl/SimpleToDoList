package com.example.simpletodolist.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.simpletodolist.R
import com.example.simpletodolist.model.MainViewModel
import com.example.simpletodolist.ui.screens.EditToDoScreen
import com.example.simpletodolist.ui.screens.HomeScreen

sealed class NavDestination(
    val route: String,
    val title: Int = 0,
    val label: Int = 0,
    val selectedIcon: ImageVector = Icons.Default.Check,
    val unselectedIcon: ImageVector = Icons.Default.Check,
    val showArrowBack: Boolean = false,
    val showFab: Boolean = false,
    val content: @Composable (NavController, MainViewModel) -> Unit
) {
    object Home : NavDestination(
        route = "home",
        title = R.string.homeScreenTitle,
        showFab = true,
        content = { navController, viewModel -> HomeScreen(navController, viewModel) }
    )

    object EditToDo : NavDestination(
        route = "edit_to_do",
        title = R.string.editToDoScreenTitle,
        showArrowBack = true,
        content = { navController, viewModel -> EditToDoScreen(navController, viewModel) }
    )
}


// Hier alle Bildschirme listen, über die in der Bottom Bar navigiert werden soll
val bottomBarNavDestinations = emptyList<NavDestination>()

// Hier alle Bildschirme listen, die als FullScreen Bildschirm angesprungen werden sollen
// wenn es keine gibt, dann
// val otherDestinations = emptyList<NavDestination>()
val otherDestinations = listOf (
    NavDestination.Home,
    NavDestination.EditToDo
)

val navDestinations = bottomBarNavDestinations + otherDestinations

// Hier alle Dialogbilschirme listen
// wenn es keine gibt, dann
// val dialogDestinations = emptyList<NavDestination>()
val dialogDestinations = emptyList<NavDestination>()

