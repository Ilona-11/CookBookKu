package com.app.cookbookku.data.repository

import com.app.cookbookku.data.local.UserDao
import com.app.cookbookku.model.User

/**
 * Repository untuk mengatur operasi database User
 */
class UserRepository(private val userDao: UserDao) {

    suspend fun register(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }
}
