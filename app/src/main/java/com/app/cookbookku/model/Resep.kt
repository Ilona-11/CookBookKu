package com.app.cookbookku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity tabel resep untuk Room Database
 */
@Entity(tableName = "resep")
data class Resep(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val nama: String,
    val kategori: String,
    val durasi: Int,
    val porsi: Int,
    val bahan: String,
    val langkah: String,
    val foto: String
)
