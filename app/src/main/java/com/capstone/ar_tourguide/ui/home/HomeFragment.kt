// HomeFragment.kt
package com.capstone.ar_tourguide.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.adapter.WisataAdapter
import com.capstone.ar_tourguide.model.Place
import com.capstone.ar_tourguide.ui.detail.DetailWisata
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class HomeFragment : Fragment() {
    private lateinit var rvWisata: RecyclerView
    private val list = ArrayList<Place>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvWisata = view.findViewById(R.id.recyclerView)
        rvWisata.setHasFixedSize(true)

        list.addAll(getListPlace())
        showRecyclerList()
    }

    private fun getListPlace(): ArrayList<Place> {
        val resourceId = resources.getIdentifier("destinasi", "raw", requireContext().packageName)

        return try {
            val inputStream = resources.openRawResource(resourceId)
            val json = inputStream.bufferedReader().use { it.readText() }
            Gson().fromJson(json, object : TypeToken<ArrayList<Place>>() {}.type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            // Handle error, e.g., notify the user that there is an error.
            ArrayList()
        }
    }


    private fun showRecyclerList() {
        rvWisata.layoutManager = LinearLayoutManager(requireContext())
        val wisataAdapter = WisataAdapter(list)

        wisataAdapter.setOnItemClickListener { position ->
            val clickedPlace = list[position]

            // Create Intent to open DetailWisata
            val intent = Intent(requireContext(), DetailWisata::class.java)

            // Add Place data to Intent
            intent.putExtra("EXTRA_PLACE", clickedPlace)

            // Start DetailWisata activity with Intent
            startActivity(intent)
        }

        rvWisata.adapter = wisataAdapter
    }
}
