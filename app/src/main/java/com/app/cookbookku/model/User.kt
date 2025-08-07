package com.app.cookbookku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity tabel user untuk Room Database
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val username: String,
    val password: String
)
