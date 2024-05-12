package com.capstone.ar_tourguide.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private var places: List<Place>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun setPlaces(places: List<Place>) {
        this.places = places
    }

    private val callback = OnMapReadyCallback { googleMap ->
        places?.forEach { place ->
            val location = LatLng(place.latitude, place.longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(place.name))
        }

        places?.firstOrNull()?.let { firstPlace ->
            // Move camera to the first place
            val firstLocation = LatLng(firstPlace.latitude, firstPlace.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 15f))
        }
    }
}
