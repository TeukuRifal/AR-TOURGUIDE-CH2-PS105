package com.capstone.ar_tourguide.onboarding

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.capstone.ar_tourguide.R

class OnBoardingActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        val constraintlayout = findViewById<ConstraintLayout>(R.id.onboarding1)
        val animationDrawable = constraintlayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(1000)
        animationDrawable.start()

        supportActionBar?.hide()

        val nextButton = findViewById<Button>(R.id.next_button)

        nextButton.setOnClickListener {
            val intent = Intent(this, OnBoardingActivity2::class.java)
            startActivity(intent)
        }
    }
}