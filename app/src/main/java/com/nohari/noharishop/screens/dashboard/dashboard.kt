package com.nohari.noharishop.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohari.noharishop.data.AuthViewModel
import com.nohari.noharishop.navigation.*

import com.nohari.noharishop.screens.Homescreen.HomeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {

    val context = LocalContext.current
    val myauth = remember { AuthViewModel(navController, context) }

    var selectedIndex by remember { mutableStateOf(0) }

    // 🔥 FETCH USER
    LaunchedEffect(Unit) {
        myauth.fetchUsername()
    }

    Scaffold(

        // 🔝 TOP BAR
        topBar = {
            TopAppBar(
                title = { Text("Shop") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                actions = {

                    // Profile icon → Profile screen
                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE)
                    }) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                    }

                    // Logout
                    IconButton(onClick = {
                        myauth.logout()
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.White)
                    }
                }
            )
        },

        // 🔻 BOTTOM BAR
        bottomBar = {
            NavigationBar {

                // HOME
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                    },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                // SETTINGS
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                    },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") }
                )

                // PROFILE (FIXED)
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUTE_PROFILE) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        },

        // ➕ FLOATING BUTTON
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(ROUTE_ADDPRODUCT)
            }) {
                Icon(Icons.Default.Add, null)
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 👋 USER WELCOME
            Text(
                text = "Welcome, ${myauth.username.value}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            // GRID ROW 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        "Add Product",
                        Color.Red,
                        onClick = { navController.navigate(ROUTE_ADDPRODUCT) }
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        "View Products",
                        Color.Blue,
                        onClick = { navController.navigate(ROUTE_LISTPRODUCTS) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // GRID ROW 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        "Orders",
                        Color.Black,
                        onClick = { /* TODO */ }
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        "Intents",
                        Color.Yellow,
                        onClick = {
                            navController.navigate(ROUTE_INTENT)
                        }
                    )
                }
            }
        }
    }
}