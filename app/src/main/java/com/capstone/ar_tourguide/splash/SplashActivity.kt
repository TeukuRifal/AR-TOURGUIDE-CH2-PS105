package com.capstone.ar_tourguide.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.capstone.ar_tourguide.LoginActivity
import com.capstone.ar_tourguide.MainActivity
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.adapter.SessionManager
import com.capstone.ar_tourguide.onboarding.OnBoardingActivity1

class SplashActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        sessionManager = SessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.isLoggedIn()) {
                // Pengguna telah login, arahkan ke halaman beranda
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Pengguna belum login, arahkan ke halaman login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            finish()
        }, 2000L)
    }

    private fun goToOnBoardingsatu() {
        Intent(this, OnBoardingActivity1::class.java).also {
            startActivity(it)
            finish()
        }
    }

}