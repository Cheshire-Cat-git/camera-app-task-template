package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
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
        // TODO call this method only after Camera permission will be granted.
        //  in case permission denied show toast message with text: 'Camera permission required for app'
        checkPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_RC
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_RC && grantResults.isNotEmpty()) {
            val permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (permissionGranted) {
                launchCamera()
            } else {
                val text = this.getText(R.string.need_camera_permission)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkPermission() {
        val permissionGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            launchCamera()
        } else {
            requestPermission()
        }
    }

    companion object {
        private const val CAMERA_RC = 999
    }
}
