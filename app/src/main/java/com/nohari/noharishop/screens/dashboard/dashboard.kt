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
import com.nohari.noharishop.navigation.ROUTE_ADDPRODUCT
import com.nohari.noharishop.navigation.ROUTE_INTENT
import com.nohari.noharishop.navigation.ROUTE_LISTPRODUCTS
import com.nohari.noharishop.screens.Homescreen.HomeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {

    val context = LocalContext.current
    val myauth = remember { AuthViewModel(navController, context) }

    var selectedIndex by remember { mutableStateOf(0) }

    // ✅ FETCH FIREBASE USER
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

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                    }

                    IconButton(onClick = {
                        myauth.logout()
                    }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
                    }
                }
            )
        },

        // 🔻 BOTTOM BAR
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
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

            // 🔥 FIREBASE USER NAME
            Text(
                text = "Welcome, ${myauth.username.value}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            //  GRID FIX (ALWAYS VISIBLE)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard("Add Product", Color.Red, onClick = {navController.navigate(
                        ROUTE_ADDPRODUCT
                    )})
                }
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard("View Products", Color.Blue,onClick ={ navController.navigate(ROUTE_LISTPRODUCTS) } )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard("Orders", Color.Black,onClick ={} )
                }
                Box(modifier = Modifier.weight(1f)) {
                    HomeCard("Intents", Color.Yellow,onClick ={ ROUTE_INTENT } )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DashboardPreview(){
    DashboardScreen(rememberNavController())
}