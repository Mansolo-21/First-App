package com.nohari.noharishop.screens.products

import com.nohari.noharishop.data.ProductViewModel
import com.nohari.noharishop.models.Product
import com.nohari.noharishop.navigation.ROUTE_LISTPRODUCTS

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(
    navController: NavHostController,
    productId: String
) {

    val context = LocalContext.current

    val productViewModel = remember {
        ProductViewModel(navController, context)
    }

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(productId) {
        productViewModel.databaseReference.child(productId).get()
            .addOnSuccessListener { snapshot ->

                val product = snapshot.getValue(Product::class.java)

                product?.let {
                    name = it.name
                    price = it.price
                    description = it.description
                    imageUrl = it.imageUrl
                }
            }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Update Product", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0BCFF)
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            val imageSource: Any? = imageUri ?: imageUrl

            if (imageSource != null) {
                AsyncImage(
                    model = imageSource,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(onClick = {
                imagePickerLauncher.launch("image/*")
            }) {
                Text("Change Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    productViewModel.updateProduct(
                        productId = productId,
                        imageUri = imageUri,
                        name = name,
                        price = price,
                        description = description
                    )

                    navController.navigate(ROUTE_LISTPRODUCTS)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Product")
            }
        }
    }
}