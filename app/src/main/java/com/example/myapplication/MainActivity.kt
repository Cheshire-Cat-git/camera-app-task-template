package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.myapplication.camera.CameraActivity
import com.example.myapplication.camera.CameraApplication

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

        // TODO call this method only after Camera permission will be granted.
        //  in case permission denied show toast message with text: 'Camera permission required for app'

        val permissionDeniedToast = Toast.makeText(
            applicationContext,
            "Camera permission required for app",
            Toast.LENGTH_SHORT
        )

        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                launchCamera()
            } else {
                permissionDeniedToast.show()
            }
        }.launch(Manifest.permission.CAMERA)

    }
}
