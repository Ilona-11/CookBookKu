package com.app.cookbookku.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.cookbookku.R
import com.app.cookbookku.model.Resep
import com.app.cookbookku.util.Constants
import com.app.cookbookku.util.PrefManager
import com.app.cookbookku.viewmodel.ResepViewModel

@Composable
fun AddEditResepScreen(
    navController: NavController,
    resepId: Int?, // null = tambah, ada nilai = edit
    resepViewModel: ResepViewModel = viewModel()
) {
    val context = LocalContext.current
    val prefManager = remember { PrefManager(context) }
    val userId = prefManager.getUserId()

    // Ambil semua resep user
    val resepList by resepViewModel.getAllResep(userId).collectAsState(initial = emptyList())

    // Cari resep yang mau di-edit
    val resepToEdit = resepList.find { it.id == resepId }

    // State input (kosong dulu)
    var judul by remember { mutableStateOf("") }
    var kategori by remember { mutableStateOf(Constants.DEFAULT_KATEGORI) }
    var durasi by remember { mutableStateOf("") }
    var porsi by remember { mutableStateOf("") }
    var bahan by remember { mutableStateOf("") }
    var langkah by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }

    // ✅ Isi ulang state ketika data edit ditemukan
    LaunchedEffect(resepToEdit) {
        resepToEdit?.let {
            judul = it.nama
            kategori = it.kategori
            durasi = it.durasi.toString()
            porsi = it.porsi.toString()
            bahan = it.bahan
            langkah = it.langkah
            foto = it.foto
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // biar bisa scroll kalau form panjang
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_cookbook),
            contentDescription = "Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = if (resepId == null) "Tambah Resep" else "Edit Resep",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul Resep") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = kategori,
            onValueChange = { kategori = it },
            label = { Text("Kategori") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = durasi,
            onValueChange = { durasi = it },
            label = { Text("Durasi (menit)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = porsi,
            onValueChange = { porsi = it },
            label = { Text("Porsi") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bahan,
            onValueChange = { bahan = it },
            label = { Text("Bahan") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = langkah,
            onValueChange = { langkah = it },
            label = { Text("Langkah") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = foto,
            onValueChange = { foto = it },
            label = { Text("URL Foto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (judul.isNotBlank() && kategori.isNotBlank()) {
                    if (resepId == null) {
                        // Tambah resep
                        resepViewModel.insertResep(
                            Resep(
                                userId = userId,
                                nama = judul,
                                kategori = kategori,
                                durasi = durasi.toIntOrNull() ?: 0,
                                porsi = porsi.toIntOrNull() ?: 0,
                                bahan = bahan,
                                langkah = langkah,
                                foto = foto
                            )
                        )
                        Toast.makeText(context, "Resep berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    } else {
                        // Update resep
                        resepViewModel.updateResep(
                            Resep(
                                id = resepId,
                                userId = userId,
                                nama = judul,
                                kategori = kategori,
                                durasi = durasi.toIntOrNull() ?: 0,
                                porsi = porsi.toIntOrNull() ?: 0,
                                bahan = bahan,
                                langkah = langkah,
                                foto = foto
                            )
                        )
                        Toast.makeText(context, "Resep berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    }
                    // Balik ke halaman detail resep
                    if (resepId != null) {
                        navController.navigate("detail_resep/$resepId") {
                            popUpTo("detail_resep/$resepId") { inclusive = true }
                        }
                    } else {
                        navController.popBackStack()
                    }
                } else {
                    Toast.makeText(context, "Judul & Kategori wajib diisi", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
