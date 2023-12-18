package com.capstone.ar_tourguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.capstone.ar_tourguide.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.widget.Toolbar


class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        //ambil informasi pengguna yang saat ini masuk
        val currentUser = auth.currentUser

        //periksa apakah pengguna telah masuk
        if (currentUser != null) {
            //email pengguna
            val userEmail = currentUser.email

            // tampilkan email pengguna di TextView
            binding.edtEmail.text = userEmail
        }

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme -> {
                // Handle theme menu item click
                return true
            }
            R.id.language -> {
                // Handle Language menu item click
                return true
            }
            R.id.logout -> {
                // Handle Logout menu item click
                logoutUser()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
}

    private fun logoutUser() {
        // Lakukan proses logout di sini
        // Misalnya, jika menggunakan FirebaseAuth, lakukan sign out
        FirebaseAuth.getInstance().signOut()

        // Navigasi ke LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        // Tutup aktivitas saat ini
        finish()
    }
}
