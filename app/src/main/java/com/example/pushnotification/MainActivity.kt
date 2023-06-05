package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun getDataFromIntent() {
        if (intent != null && intent.hasExtra("key1")) {
            for (key in intent.extras!!.keySet()) {
                Log.d(TAG, "Key: $key -> Data: ${intent.extras!!.getString(key)}")
            }
        }
    }

    private fun getFirebaseMessagingToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val token = result.result
                Log.d(TAG, "onCreate: $token")
            }
        }
    }
}
