package com.app.cookbookku.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp // Ganti Logout jadi ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cookbookku.util.PrefManager

@Composable
fun ProfileScreen(
    navController: NavController,
    prefManager: PrefManager
) {
    val fullName = prefManager.getName()
    val email = prefManager.getEmail()
    val username = prefManager.getUsername()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF800000)) // Maroon
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto Profil Placeholder
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Icon",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nama
        Text(
            text = fullName ?: "Nama Lengkap",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

        // Email
        Text(
            text = email ?: "Email",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Username
        Text(
            text = "@${username ?: "username"}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White.copy(alpha = 0.8f)
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Tombol Logout
        Button(
            onClick = {
                prefManager.clear() // Hapus session login
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp, // Ikon logout alternatif
                contentDescription = "Logout",
                tint = Color(0xFF800000)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Logout",
                color = Color(0xFF800000),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
