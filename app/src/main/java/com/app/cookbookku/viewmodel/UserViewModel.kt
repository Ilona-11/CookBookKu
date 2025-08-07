package com.app.cookbookku.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.cookbookku.data.local.AppDatabase
import com.app.cookbookku.data.repository.UserRepository
import com.app.cookbookku.model.User
import kotlinx.coroutines.launch

/**
 * ViewModel untuk mengatur data User
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun register(name: String, email: String, username: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val existingUser = repository.getUserByUsername(username)
            if (existingUser != null) {
                onResult(false, "Username sudah digunakan")
            } else {
                repository.register(User(name = name, email = email, username = username, password = password))
                onResult(true, "Registrasi berhasil")
            }
        }
    }

    fun login(username: String, password: String, onResult: (Boolean, User?) -> Unit) {
        viewModelScope.launch {
            val user = repository.login(username, password)
            if (user != null) {
                onResult(true, user)
            } else {
                onResult(false, null)
            }
        }
    }
}
