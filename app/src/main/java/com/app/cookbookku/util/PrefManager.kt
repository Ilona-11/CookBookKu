package com.app.cookbookku.util

import android.content.Context
import android.content.SharedPreferences

/**
 * PrefManager digunakan untuk menyimpan dan mengambil data session user.
 */
class PrefManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    /**
     * Simpan data user lengkap (nama, email, username, password)
     */
    fun saveUser(name: String, email: String, username: String, password: String) {
        prefs.edit()
            .putBoolean(Constants.PREF_KEY_IS_LOGGED_IN, true)
            .putString(Constants.PREF_KEY_NAME, name)
            .putString(Constants.PREF_KEY_EMAIL, email)
            .putString(Constants.PREF_KEY_USERNAME, username)
            .putString(Constants.PREF_KEY_PASSWORD, password)
            .apply()
    }

    /**
     * Simpan ID user
     */
    fun saveUserId(id: Int) {
        prefs.edit()
            .putInt(Constants.PREF_KEY_USER_ID, id)
            .apply()
    }

    /**
     * Ambil ID user
     */
    fun getUserId(): Int {
        return prefs.getInt(Constants.PREF_KEY_USER_ID, 0)
    }

    /**
     * Cek apakah user sudah login
     */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(Constants.PREF_KEY_IS_LOGGED_IN, false)
    }

    fun getName(): String {
        return prefs.getString(Constants.PREF_KEY_NAME, "") ?: ""
    }

    fun getEmail(): String {
        return prefs.getString(Constants.PREF_KEY_EMAIL, "") ?: ""
    }

    fun getUsername(): String {
        return prefs.getString(Constants.PREF_KEY_USERNAME, "") ?: ""
    }

    fun getPassword(): String {
        return prefs.getString(Constants.PREF_KEY_PASSWORD, "") ?: ""
    }

    /**
     * Hapus semua data (Logout)
     */
    fun clear() {
        prefs.edit().clear().apply()
    }
}
