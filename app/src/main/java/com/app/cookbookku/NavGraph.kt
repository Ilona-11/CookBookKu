package com.app.cookbookku

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.cookbookku.util.PrefManager
import com.app.cookbookku.view.*
import com.app.cookbookku.viewmodel.ResepViewModel
import com.app.cookbookku.viewmodel.UserViewModel

/**
 * NavGraph - Mengatur rute navigasi antar screen
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    application: Application,
    modifier: Modifier = Modifier
) {
    val prefManager = PrefManager(application)

    // ViewModel untuk User
    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(application)
    )

    // ViewModel untuk Resep
    val resepViewModel: ResepViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        // Splash Screen
        composable("splash") {
            SplashScreen(navController, prefManager)
        }

        // Login Screen
        composable("login") {
            LoginScreen(navController, prefManager)
        }

        // Register Screen
        composable("register") {
            RegisterScreen(navController, prefManager)
        }

        // Home Screen
        composable("home") {
            HomeScreen(navController, resepViewModel)
        }

        // Detail Resep Screen
        composable("detail_resep/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            DetailResepScreen(navController, id, resepViewModel)
        }

        // Add Resep Screen
        composable("add") {
            AddEditResepScreen(navController, null, resepViewModel)
        }

        // Edit Resep Screen
        composable("edit_resep/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            AddEditResepScreen(navController, id, resepViewModel)
        }

        // Profile Screen
        composable("profile") {
            ProfileScreen(navController, prefManager)
        }
    }
}
