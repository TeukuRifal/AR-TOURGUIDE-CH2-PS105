package com.capstone.ar_tourguide.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.ar_tourguide.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

    }
}