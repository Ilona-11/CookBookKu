package com.app.cookbookku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.cookbookku.ui.theme.CookBookKuTheme

/**
 * MainActivity - Entry point aplikasi
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookKuTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        val currentRoute = currentRoute(navController)
                        if (currentRoute in listOf("home", "add", "profile")) {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        application = application,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Bottom Navigation Bar
 */
@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Tambah", Icons.Default.Add, "add"),
        BottomNavItem("Profile", Icons.Default.Person, "profile")
    )

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = {
                    Text(item.title, color = MaterialTheme.colorScheme.onPrimary)
                }
            )
        }
    }
}

/**
 * Mendapatkan route halaman saat ini
 */
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

/**
 * Data model untuk item bottom navigation
 */
data class BottomNavItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)
