package com.capstone.ar_tourguide.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capstone.ar_tourguide.LoginActivity
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Ambil informasi pengguna yang saat ini masuk
        val currentUser = auth.currentUser

        // Periksa apakah pengguna telah masuk
        if (currentUser != null) {
            // Email pengguna
            val userEmail = currentUser.email

            // Tampilkan email pengguna di TextView
            binding.edtEmail.text = userEmail
        }

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Buat remove title
        (activity as AppCompatActivity).supportActionBar?.title = null
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                // Handle Logout menu item click
                logoutUser()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun logoutUser() {
        // Lakukan proses logout di sini
        // Misalnya, jika menggunakan FirebaseAuth, lakukan sign out
        FirebaseAuth.getInstance().signOut()

        // Navigasi ke LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)

        // Tutup fragment saat ini
        requireActivity().finish()
    }
}
