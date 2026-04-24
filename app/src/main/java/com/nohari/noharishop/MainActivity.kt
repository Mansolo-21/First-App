package com.nohari.noharishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import com.nohari.noharishop.data.isFirstTime
import com.nohari.noharishop.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        setContent {

            // ✅ Decide first screen
            val startDestination =
                if (isFirstTime(this)) "onboarding" else "login"

            AppNavHost(startDestination = startDestination)
        }
    }
}