package com.testapp.telephonycallback

import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log

const val TAG: String = "MyTelephonyCallback"

class MyTelephonyCallback : TelephonyCallback(), TelephonyCallback.CallStateListener {

    override fun onCallStateChanged(state: Int) {
        when (state) {
            TelephonyManager.CALL_STATE_IDLE -> {
                Log.i(TAG, "TelephonyManager.CALL_STATE_IDLE")
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                Log.i(TAG, "TelephonyManager.CALL_STATE_OFFHOOK")
            }
            TelephonyManager.CALL_STATE_RINGING -> {
                Log.i(TAG, "TelephonyManager.CALL_STATE_RINGING")
            }
        }
    }
}
