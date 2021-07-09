package com.testapp.telephonycallback

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

const val REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private var telephonyManager: TelephonyManager? = null
    private var telephonyCallback: MyTelephonyCallback = MyTelephonyCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        findViewById<Button>(R.id.button).setOnClickListener {
            if (ifPhonePermissionsGranted()) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Permission granted")
                registerPhoneListener()
                call(this, "411")
            } else {
                Toast.makeText(this, "Permission NOT granted", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Permission NOT granted")
                requestPhonePermission()
            }
        }
    }

    private fun requestPhonePermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_CODE)
    }

    private fun ifPhonePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun registerPhoneListener() {
        telephonyManager?.let {
            Binder.clearCallingIdentity()
            it.registerTelephonyCallback(mainExecutor, telephonyCallback)
        } ?: run {
            Log.e(TAG, " Failed to registerPhoneListener telephonyManager == null")
        }
    }

    private fun unregisterPhoneListener() {
        telephonyManager?.unregisterTelephonyCallback(telephonyCallback)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "requestCode=$requestCode, grantResults=$grantResults")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterPhoneListener()
    }


    private fun call(context: Context, number: String) {
        val intent: Intent = if (hasPhonePermission(context)) {
            Intent(Intent.ACTION_CALL)
        } else {
            Intent(Intent.ACTION_DIAL)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.parse("tel:$number")
        context.startActivity(intent)
    }

    private fun hasPhonePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    }
}
