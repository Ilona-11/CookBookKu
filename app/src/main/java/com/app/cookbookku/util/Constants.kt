package com.app.cookbookku.util

/**
 * Berisi konstanta global yang digunakan di seluruh aplikasi.
 */
object Constants {
    // Nama database Room
    const val DATABASE_NAME = "cookbookku_db"

    // SharedPreferences Keys
    const val PREF_NAME = "cookbookku_prefs"
    const val PREF_KEY_IS_LOGGED_IN = "is_logged_in"
    const val PREF_KEY_NAME = "name"
    const val PREF_KEY_EMAIL = "email"
    const val PREF_KEY_USERNAME = "username"
    const val PREF_KEY_PASSWORD = "password"
    const val PREF_KEY_USER_ID = "user_id"

    // Dummy kategori default (bisa untuk spinner/dropdown)
//    val DEFAULT_KATEGORI = listOf(
//        "Makanan Utama",
//        "Makanan Ringan",
//        "Minuman",
//        "Dessert"
//    )

    // Waktu delay SplashScreen (ms)
    const val SPLASH_DELAY = 2000L

    // Default kategori resep
    const val DEFAULT_KATEGORI = "Umum"
}
