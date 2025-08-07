package com.app.cookbookku.view

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.cookbookku.util.PrefManager
import com.app.cookbookku.viewmodel.UserViewModel
import com.app.cookbookku.R

@Composable
fun LoginScreen(navController: NavController, prefManager: PrefManager) {
    val context = LocalContext.current
    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)
    )

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    // Logo
    Image(
        painter = painterResource(id = R.drawable.logo_cookbook),
        contentDescription = "Logo CookBook",
        modifier = Modifier
            .size(180.dp) // besar logo
            .padding(top = 32.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        // Username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Pesan error
        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Login
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    userViewModel.login(username, password) { success, user ->
                        if (success && user != null) {
                            prefManager.saveUserId(user.id)
                            prefManager.saveUser(
                                name = user.name, // Sesuai model User
                                email = user.email,
                                username = user.username,
                                password = user.password
                            )
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            error = "Username atau password salah"
                        }
                    }
                } else {
                    error = "Username dan password wajib diisi"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Login", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol Daftar
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Belum punya akun? Daftar")
        }
    }
}
