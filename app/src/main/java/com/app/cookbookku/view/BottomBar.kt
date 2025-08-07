package com.app.cookbookku.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val route: String,
    val icon: @Composable () -> Unit,
    val label: String
)

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem(
            route = "home",
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = "Home"
        ),
        BottomNavItem(
            route = "add",
            icon = { Icon(Icons.Default.Add, contentDescription = "Tambah") },
            label = "Tambah"
        ),
        BottomNavItem(
            route = "profile",
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = "Profile"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.label) },
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}
