package com.app.cookbookku.data.repository

import com.app.cookbookku.data.local.ResepDao
import com.app.cookbookku.model.Resep
import kotlinx.coroutines.flow.Flow

/**
 * Repository untuk mengatur operasi database Resep
 */
class ResepRepository(private val resepDao: ResepDao) {

    fun getAllResep(userId: Int): Flow<List<Resep>> {
        return resepDao.getAllResep(userId)
    }

    fun getResepByKategori(userId: Int, kategori: String): Flow<List<Resep>> {
        return resepDao.getResepByKategori(userId, kategori)
    }

    suspend fun insertResep(resep: Resep) {
        resepDao.insertResep(resep)
    }

    suspend fun updateResep(resep: Resep) {
        resepDao.updateResep(resep)
    }

    suspend fun deleteResep(resep: Resep) {
        resepDao.deleteResep(resep)
    }
}
