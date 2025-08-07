package com.app.cookbookku.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.cookbookku.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?
}
