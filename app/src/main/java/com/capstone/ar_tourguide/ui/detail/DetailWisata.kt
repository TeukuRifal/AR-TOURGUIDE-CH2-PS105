package com.capstone.ar_tourguide.ui.detail
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.databinding.ActivityDetailWisataBinding
import com.capstone.ar_tourguide.model.Place
import com.capstone.ar_tourguide.ui.explore.ExploreActivity
import com.capstone.ar_tourguide.ui.maps.MapsActivity

class DetailWisata : AppCompatActivity() {

    private lateinit var binding: ActivityDetailWisataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get Place data from Intent
        val place = intent.getParcelableExtra<Place>("EXTRA_PLACE")

        // Display data in the layout
        place?.let {
            binding.tvDescription.text = it.description
            binding.tvHistory.text = it.history
            binding.tvFunfact.text = it.funFacts

            // Load image using Glide
            Glide.with(this)
                .load(it.photo)
                .placeholder(R.drawable.monas)
                .into(binding.ivphoto)

            // Set OnClickListener on the ImageView (photo)
            binding.ivphoto.setOnClickListener { view ->
                showImagePopup(place.photo)
            }
        }
    }

    // Function to handle click on Maps logo
    fun openMapsActivity(view: View) {
        val place = intent.getParcelableExtra<Place>("EXTRA_PLACE")

        // Open MapsActivity and send Place data as "extra"
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("EXTRA_PLACE", place)
        startActivity(intent)
    }
    fun openExploreActivity(view: View) {
        val intent = Intent(this, ExploreActivity::class.java)
        startActivity(intent)
    }

    // Function to show a popup with the selected image
    private fun showImagePopup(imageUrl: String) {
        val builder = AlertDialog.Builder(this)
        val imageView = ImageView(this)

        // Load image using Glide into the ImageView
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.monas)
            .into(imageView)

        // Set the ImageView as the view of the AlertDialog
        builder.setView(imageView)

        // Set the dialog to be cancellable
        builder.setCancelable(true)

        // Add a button to close the dialog
        builder.setPositiveButton("Close") { dialog, _ ->
            dialog.dismiss()
        }

        // Show the AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
}
