package com.hamisibriva.qrbarcodescanner.ui.splash

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.hamisibriva.qrbarcodescanner.ui.mainactivity.MainActivity
import com.hamisibriva.qrbarcodescanner.R

class SplashActivity : AppCompatActivity() {
    companion object{
        private const val  CAMERA_PERMISSION_REQUEST_CODE = 123
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            //check for permissions
            checkForPermission()
        }, 3000)
    }

    private fun checkForPermission() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
            goToMainActivity()
        }else{
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                goToMainActivity()
            }
            else if (isUserPermanentlydenied()){
                showGOToAppSettingDialog()
            }
            else{
                requestPermission()
            }
        }
    }

    private fun showGOToAppSettingDialog() {
        AlertDialog.Builder(this).setTitle("Grant CAMERA Access").setMessage("We need Camera Access to Scan the QRCode/Barcode. Go to App Setting and Manage Access")
            .setPositiveButton("Grant"){dialog, which ->
                gotoAppSettings()
            }.setNegativeButton("Cancel"){
                dialog, which ->
                Toast.makeText(this, "We Need Access to Start this Application", Toast.LENGTH_SHORT).show()
                finish()
            }.show()
    }

    private fun gotoAppSettings() {
        var intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun isUserPermanentlydenied(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA).not()
        } else {
            return false
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        checkForPermission()
    }
}