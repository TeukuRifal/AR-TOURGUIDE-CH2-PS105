package com.capstone.ar_tourguide.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.onboarding.OnBoardingActivity1

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            goToOnBoardingsatu()
        }, 2000L)
    }
    private fun goToOnBoardingsatu() {
        Intent(this, OnBoardingActivity1::class.java).also {
            startActivity(it)
            finish()
        }
    }

}