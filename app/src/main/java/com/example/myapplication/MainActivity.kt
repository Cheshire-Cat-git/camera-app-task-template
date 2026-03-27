package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.myapplication.camera.CameraActivity

/**
 * Main activity of the app.
 * This activity will be launched after tapping on the app icon on the home screen.
 * Put your code here.
 */
class MainActivity : CameraActivity() {

    override lateinit var previewView: PreviewView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        previewView = findViewById(R.id.preview_view)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            launchCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                Toast.makeText(
                    this,
                    "Camera permission required for app",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 0
    }
}
