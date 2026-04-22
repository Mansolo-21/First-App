package com.nohari.noharishop.screens.products


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohari.noharishop.data.ProductViewModel
import com.nohari.noharishop.models.Product
import com.nohari.noharishop.navigation.ROUTE_UPDATEPRODUCT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController) {

    val context = LocalContext.current
    val productViewModel = ProductViewModel(navController, context)

    val product = remember { mutableStateOf(Product("", "", "", "", "")) }
    val products = remember { mutableStateListOf<Product>() }

    // 🔥 Fetch products
    LaunchedEffect(Unit) {
        productViewModel.allProducts(product, products)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Product List") }
            )
        }
    ) { paddingValues ->

        LazyColumn(contentPadding = paddingValues) {

            items(products) { productItem ->

                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        // Product Image (Cloudinary)
                        AsyncImage(
                            model = productItem.imageUrl,
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = productItem.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = "Price: KES ${productItem.price}")
                        Text(text = productItem.description)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            //  Delete
                            Text(
                                text = "Delete",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .clickable {
                                        productViewModel.deleteProduct(productItem.id)
                                    }
                            )

                            // ✏ Update
                            Text(
                                text = "Update",
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clickable {
                                       navController.navigate("$ROUTE_UPDATEPRODUCT/${productItem.id}")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun productlistpreview(){
    ProductListScreen(rememberNavController())
}