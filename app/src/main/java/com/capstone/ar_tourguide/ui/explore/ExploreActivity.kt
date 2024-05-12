package com.capstone.ar_tourguide.ui.explore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.ar_tourguide.databinding.ActivityExploreBinding

class ExploreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cafePesona.setOnClickListener{
            Toast.makeText(this,"Fitur ini akan segera hadir",Toast.LENGTH_SHORT).show()
        }
        binding.lCafe.setOnClickListener{
            Toast.makeText(this,"Fitur ini akan segera hadir",Toast.LENGTH_SHORT).show()
        }
        binding.lokoKafe.setOnClickListener{
            Toast.makeText(this,"Fitur ini akan segera hadir",Toast.LENGTH_SHORT).show()
        }
        binding.covareCW.setOnClickListener{
            Toast.makeText(this,"Fitur ini akan segera hadir",Toast.LENGTH_SHORT).show()
        }
        binding.lokoKafeGambir.setOnClickListener{
            Toast.makeText(this,"Fitur ini akan segera hadir",Toast.LENGTH_SHORT).show()
        }
    }


}