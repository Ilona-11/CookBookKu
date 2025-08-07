package com.app.cookbookku.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.cookbookku.data.local.AppDatabase
import com.app.cookbookku.data.repository.ResepRepository
import com.app.cookbookku.model.Resep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel untuk mengatur data Resep
 */
class ResepViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ResepRepository

    init {
        val resepDao = AppDatabase.getDatabase(application).resepDao()
        repository = ResepRepository(resepDao)
    }

    fun getAllResep(userId: Int): Flow<List<Resep>> {
        return repository.getAllResep(userId)
    }

    fun getResepByKategori(userId: Int, kategori: String): Flow<List<Resep>> {
        return repository.getResepByKategori(userId, kategori)
    }

    fun insertResep(resep: Resep) {
        viewModelScope.launch {
            repository.insertResep(resep)
        }
    }

    fun updateResep(resep: Resep) {
        viewModelScope.launch {
            repository.updateResep(resep)
        }
    }

    fun deleteResep(resep: Resep) {
        viewModelScope.launch {
            repository.deleteResep(resep)
        }
    }
}
