package com.capstone.ar_tourguide.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.capstone.ar_tourguide.LoginActivity
import com.capstone.ar_tourguide.R

class OnBoardingActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding2)

        val constraintlayout = findViewById<ConstraintLayout>(R.id.onboarding2)
        val animationDrawable = constraintlayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(1000)
        animationDrawable.start()

        supportActionBar?.hide()

        val startedButton = findViewById<Button>(R.id.started_button)

        startedButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}