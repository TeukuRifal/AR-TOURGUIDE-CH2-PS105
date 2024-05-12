package com.capstone.ar_tourguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.capstone.ar_tourguide.databinding.ActivityMainBinding
import com.capstone.ar_tourguide.ui.camera.CameraFragment
import com.capstone.ar_tourguide.ui.home.HomeFragment
import com.capstone.ar_tourguide.ui.profile.ProfileFragment



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        binding.bottomNav.background = null

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_camera -> replaceFragment(CameraFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        return true
    }
}