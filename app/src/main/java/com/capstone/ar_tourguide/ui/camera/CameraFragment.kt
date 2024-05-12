package com.capstone.ar_tourguide.ui.camera
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.capstone.ar_tourguide.R
import com.capstone.ar_tourguide.ui.detail.DetailWisata

class CameraFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private val CAMERA_PERMISSION_CODE = 101
    private val BARCODE_REQUEST_CODE = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Meminta izin kamera secara runtime
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            // Izin sudah diberikan, lanjutkan dengan menampilkan dialog opsi
            showOptionsDialog()
        }
    }

    private fun showOptionsDialog() {
        val options = arrayOf("Ambil Foto", "Scan Barcode", "Tampilkan AR")
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Pilih Opsi")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> dispatchTakePictureIntent()
                    1 -> startBarcodeScanner()
                    2 -> showCameraAR()
                }
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .create()

        dialog.show()
    }
    private fun showCameraAR() {
        val intent = Intent(requireContext(), CameraAR::class.java)
        startActivity(intent)
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun startBarcodeScanner() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.captureActivity = CaptureActivity::class.java
        integrator.initiateScan()
    }

// ...

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Gambar telah diambil, Anda dapat melakukan sesuatu dengan hasilnya di sini
            val imageUri: Uri? = data?.data
            // Lakukan sesuatu dengan Uri gambar, misalnya tampilkan di ImageView
        } else if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Hasil dari pemindaian barcode
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents != null) {
                    // Lakukan sesuatu dengan nilai barcode yang dihasilkan
                    val barcodeValue = result.contents

                    // Memeriksa apakah barcode berisi "AR TOURGUIDE"
                    if (barcodeValue.equals("AR TOURGUIDE", ignoreCase = true)) {
                        // Barcode sesuai, buka DetailActivity
                        val intent = Intent(requireContext(), DetailWisata::class.java)
                        startActivity(intent)
                    } else {
                        // Barcode tidak sesuai, tampilkan pesan peringatan
                        showBarcodeErrorDialog()
                    }
                }
            }
        }
    }

    private fun showBarcodeErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Peringatan")
            .setMessage("Barcode tidak valid. Silakan scan ulang dengan barcode AR TOURGUIDE.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin diberikan, lanjutkan dengan menampilkan dialog opsi
                showOptionsDialog()
            } else {
                // Izin ditolak, tindakan yang sesuai dapat diambil di sini
                // Misalnya, tampilkan pesan kepada pengguna atau tampilkan UI alternatif
            }
        }
    }
}
