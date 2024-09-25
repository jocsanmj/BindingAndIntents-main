package com.app.bindingandintents

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.bindingandintents.databinding.ActivitySecondBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.File
import java.io.FileOutputStream

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Request permission to make calls
    private val callPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                makePhoneCall()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(this, "El permiso es necesario para hacer llamadas.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permiso denegado permanentemente. Habilítalo en la configuración.", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", packageName, null)
                    }
                    startActivity(intent)
                }
            }
        }

    @SuppressLint("SetTextI18n", "QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Initializes the location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Adjusting insets for edge-to-edge screens
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Navigation between Activities + chatcito
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        binding.welcomeText.text = message ?: "¡Mensaje de bienvenida!"

        // 4. Make a phone call + chatcito
        binding.btnCall.setOnClickListener {
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            if (phoneNumber.isNotEmpty()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    makePhoneCall() // You already have the permit, make the call
                } else {
                    callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
            } else {
                Toast.makeText(this, "Favor, ingrese un número de teléfono.", Toast.LENGTH_SHORT).show()
            }
        }
        // 5. Open a web page with URL  + chatcito
        binding.openWebButton.setOnClickListener {
            val url = binding.urlEditText.text.toString()
            if (url.isNotEmpty()) {
                val webIntent = Intent(Intent.ACTION_VIEW)
                webIntent.data = Uri.parse(if (url.startsWith("http://") || url.startsWith("https://")) url else "http://$url")
                startActivity(webIntent)
            } else {
                Toast.makeText(this, "Favor, ingrese una URL válida.", Toast.LENGTH_SHORT).show()
            }
        }
        // 6. Send an e-mail + chatcito
        binding.btnSendEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822" // MIME type para manejar correos electrónicos
                putExtra(Intent.EXTRA_EMAIL, arrayOf("destinatario@ejemplo.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo")
                putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo")
            }
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(emailIntent, "Selecciona un servicio de correo"))
            } else {
                Toast.makeText(this, "No dispone de servicio de email.", Toast.LENGTH_SHORT).show()
            }
        }
        // 7. Share image from an ImageView + chatcito
        binding.btnShareImage.setOnClickListener {
            val drawable = binding.imageView.drawable as? BitmapDrawable
            if (drawable != null) {
                val bitmap = drawable.bitmap
                val imageUri = saveImageToCache(bitmap)
                if (imageUri != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "image/*"
                        putExtra(Intent.EXTRA_STREAM, imageUri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    startActivity(Intent.createChooser(shareIntent, "Compartir Imagen"))
                } else {
                    Toast.makeText(this, "Error al compartir la imagen.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No hay imagen para compartir.", Toast.LENGTH_SHORT).show()
            }
        }
        // 8. Get device location using Google Maps + chatcito
        binding.btnGetLocation.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                        intent.setPackage("com.google.android.apps.maps") // Abrir Google Maps
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "No se pudo obtener la ubicación.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
        // 9. Open the device configuration + chatcito
        binding.btnOpenSettings.setOnClickListener {
            val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)
        }

        // 10. Playing a YouTube video + chatcito
        binding.btnPlayYouTube.setOnClickListener {
            val videoId = binding.etYouTubeId.text.toString()
            if (videoId.isNotEmpty()) {
                val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
                startActivity(youtubeIntent)
            } else {
                Toast.makeText(this, "Por favor, ingrese un ID de video de YouTube.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Function to make a phone call + chatcito
    private fun makePhoneCall() {
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(callIntent)
    }
    private fun saveImageToCache(bitmap: Bitmap): Uri? {
        return try {
            val cachePath = File(applicationContext.cacheDir, "images")
            cachePath.mkdirs() // Create the folder if it does not exist
            val file = File(cachePath, "image_to_share.png")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()

            // Get image URI using FileProvider + chatcito
            FileProvider.getUriForFile(this, "${packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.btnGetLocation.performClick()
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
//ni idea esto lo paso chatcito
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
}
