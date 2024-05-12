package com.capstone.ar_tourguide.adapter

import android.content.Context

// Class untuk manajemen Shared Preferences
class SessionManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // Fungsi untuk menandai bahwa pengguna sudah login
    fun setLogin() {
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    // Fungsi untuk menandai bahwa pengguna sudah logout
    fun setLogout() {
        editor.putBoolean("isLoggedIn", false)
        editor.apply()
    }

    // Fungsi untuk memeriksa apakah pengguna sudah login atau belum
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
