package com.nohari.noharishop.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nohari.noharishop.data.setNotFirstTime

@Composable
fun OnboardingScreen(navController: NavHostController) {

    val context = LocalContext.current

    val pages = listOf(
        "Welcome to NohariShop 🛍️",
        "Buy & Sell Easily 🚀",
        "Fast & Secure 💳"
    )

    var page by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = pages[page],
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (page < pages.lastIndex) {

                Button(onClick = {
                    page++
                }) {
                    Text("Next")
                }

            } else {

                Button(onClick = {

                    // ✅ Save onboarding done
                    setNotFirstTime(context)

                    // 🚀 Go to login
                    navController.navigate("login") {
                        popUpTo(0)
                    }

                }) {
                    Text("Get Started")
                }
            }
        }
    }
}