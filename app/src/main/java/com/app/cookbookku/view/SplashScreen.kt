package com.app.cookbookku.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cookbookku.util.Constants
import com.app.cookbookku.util.PrefManager
import kotlinx.coroutines.delay
import com.app.cookbookku.R

@Composable
fun SplashScreen(navController: NavController, prefManager: PrefManager) {
    // Menjalankan efek ketika SplashScreen pertama kali muncul
    LaunchedEffect(Unit) {
        delay(Constants.SPLASH_DELAY) // Delay sesuai konstanta (ms)
        if (prefManager.isLoggedIn()) {
            // Jika sudah login, navigasi ke Home
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // Jika belum login, navigasi ke Login
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // Tampilan Splash Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CookBookKu",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}