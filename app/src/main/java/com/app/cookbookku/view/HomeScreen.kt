package com.app.cookbookku.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.cookbookku.util.Constants
import com.app.cookbookku.util.PrefManager
import com.app.cookbookku.viewmodel.ResepViewModel
import com.app.cookbookku.R

@Composable
fun HomeScreen(
    navController: NavController,
    resepViewModel: ResepViewModel = viewModel()
) {
    val context = LocalContext.current
    val prefManager = remember { PrefManager(context) }
    val userId = prefManager.getUserId()

    // Ambil semua resep milik user
    val resepList by resepViewModel.getAllResep(userId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_cookbook),
            contentDescription = "Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Resep Makanan", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        if (resepList.isEmpty()) {
            // Tampilan jika tidak ada resep
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Belum ada resep.\nTekan tombol tambah untuk menambahkan.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(resepList) { resep ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detail_resep/${resep.id}")
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                        ) {
                            // Foto resep
                            AsyncImage(
                                model = resep.foto,
                                contentDescription = resep.nama,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            // Info resep
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = resep.nama,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = resep.kategori.ifBlank { Constants.DEFAULT_KATEGORI },
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "${resep.durasi} menit • ${resep.porsi} porsi",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
