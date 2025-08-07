package com.app.cookbookku.data.local

import androidx.room.*
import com.app.cookbookku.model.Resep
import kotlinx.coroutines.flow.Flow

@Dao
interface ResepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResep(resep: Resep)

    @Update
    suspend fun updateResep(resep: Resep)

    @Delete
    suspend fun deleteResep(resep: Resep)

    @Query("SELECT * FROM resep WHERE userId = :userId ORDER BY id DESC")
    fun getAllResep(userId: Int): Flow<List<Resep>>

    @Query("SELECT * FROM resep WHERE userId = :userId AND kategori = :kategori ORDER BY id DESC")
    fun getResepByKategori(userId: Int, kategori: String): Flow<List<Resep>>

}
