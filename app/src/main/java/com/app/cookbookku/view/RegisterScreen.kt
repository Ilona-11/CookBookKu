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
fun RegisterScreen(
    navController: NavController,
    prefManager: PrefManager
) {
    val context = LocalContext.current
    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)
    )

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    // Logo
    Image(
        painter = painterResource(id = R.drawable.logo_cookbook),
        contentDescription = "Logo CookBook",
        modifier = Modifier
            .size(180.dp)
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

        Text("Register", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        // Nama Lengkap
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        // Konfirmasi Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Konfirmasi Password") },
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

        // Tombol Register
        Button(
            onClick = {
                if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    error = "Semua kolom wajib diisi"
                } else if (password != confirmPassword) {
                    error = "Password dan konfirmasi password tidak sama"
                } else {
                    userViewModel.register(fullName, email, username, password) { success, message ->
                        if (success) {
                            prefManager.saveUser(fullName, email, username, password)
                            navController.navigate("login") {
                                popUpTo("register") { inclusive = true }
                            }
                        } else {
                            error = message
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Daftar", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Pindah ke Login
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Sudah punya akun? Login")
        }
    }
}
