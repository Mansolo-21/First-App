package com.nohari.noharishop.screens.products

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohari.noharishop.R
import com.nohari.noharishop.data.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct(navController: NavHostController) {

    // 🔹 State variables
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    //imagepicker launcher
    val imagePickerLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri: Uri? ->
        imageUri=uri
    }

    Scaffold(

        // 🔹 TOP BAR
        topBar = {
            TopAppBar(
                title = { Text("Add Product") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                }
            )
        },

        // 🔹 BOTTOM BAR
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    },
                    label = { Text("Settings") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    },
                    label = { Text("Profile") }
                )
            }
        },


        floatingActionButton = {
            FloatingActionButton(onClick = {
                // TODO: Handle Add Product
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

    ) { paddingValues ->

        // 🔹 MAIN CONTENT
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Add Product",
                style = MaterialTheme.typography.headlineSmall
            )

            // 🔹 PRODUCT NAME
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            // 🔹 PRODUCT PRICE
            OutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(20.dp))
            // 🔹 PRODUCT DESCRIPTION
            OutlinedTextField(
                value = productDescription,
                onValueChange = { productDescription = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(10.dp))
//PRODUCT IMAGE picker plus preview
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size((140.dp))
                    .clickable{imagePickerLauncher.launch("image/*")}
            ) {
                AsyncImage(
                    model = imageUri ?: R.drawable.background,
                    contentScale = ContentScale.Crop,
                    contentDescription = "product",

                )
            }
            OutlinedButton(onClick = {imagePickerLauncher.launch("image/*")}) {
                Text("Choose Image")
            }

            //
            val context= LocalContext.current
            val myproductviewmodel= ProductViewModel(navController, context)
            Button(
                onClick = {
                    myproductviewmodel.uploadProduct(
                        imageUri = imageUri,
                        name =  productName,
                        price = productPrice,
                        description = productDescription
                    )
                    //clear textfields
                    productName=""
                    productPrice=""
                    productDescription=""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Product")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun addpreview(){
    AddProduct(rememberNavController())
}
