package com.nohari.noharishop.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultPivotX
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohari.noharishop.data.AuthViewModel
import com.nohari.noharishop.screens.Homescreen.HomeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val context= LocalContext.current
    val myauth= AuthViewModel(navController,context)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome to The NohariShop") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings Icon",
                            tint = Color.White
                        )
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Settings Icon")}

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Logout Icon")}
                    }
                },

            )
        },
                //bottom bar
                bottomBar ={
                    NavigationBar() {
                        NavigationBarItem(
                            selected = true,
                            onClick = {},
                            icon = {
                                Icon(imageVector = Icons.Default.Home, contentDescription = "home")
                            },
                            label = {Text("Home")}
                        )
                        NavigationBarItem(
                            selected = true,
                            onClick = {},
                            icon = {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                            },
                            label = {Text("Settings")}
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = {
                                Icon(imageVector = Icons.Default.Person, contentDescription = "settings")
                            },
                            label = {Text("Profile")}
                        )
                    }
                },
        //floatingActionButton
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add,contentDescription ="add icon")
            }
        }

    )  { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                HomeCard(title = "Add Product", background = Color.Red)
                HomeCard(title = "Add Product", background = Color.Blue)
                Row() {
                    HomeCard(title = "Add Product", background = Color.Black,)
                    HomeCard(title = "Add Product", background = Color.Yellow,)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun dashboardpreview(){
    DashboardScreen(rememberNavController())
}