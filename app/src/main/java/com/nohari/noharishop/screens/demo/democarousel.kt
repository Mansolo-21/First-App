package com.nohari.noharishop.screens.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

// ✅ DATA MODEL (keep outside composable in real apps)
data class Product(
    val name: String,
    val price: String,
    val image: String
)

// ✅ SAMPLE DATA
val productList = listOf(
    Product("Shoes", "1500", "https://images.unsplash.com/photo-1542291026-7eec264c27ff"),
    Product("Watch", "3000", "https://images.unsplash.com/photo-1523275335684-37898b6baf30"),
    Product("Bag", "2000", "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f"),
    Product("Headphones", "2500", "https://images.unsplash.com/photo-1518441902117-0d7d6e5b9c8f"),
    Product("Laptop", "85000", "https://images.unsplash.com/photo-1517336714731-489689fd1ca8")
)

@Composable
fun DemoCarousel() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔵 Title
        Text(
            text = "User Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Featured Products",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🟡 LazyRow Carousel
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productList) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {

    Card(
        modifier = Modifier
            .width(180.dp)
            .height(260.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            // 🖼 Image
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 📝 Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyMedium
            )

            // 💰 Price
            Text(
                text = "Ksh ${product.price}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarousel() {
    DemoCarousel()
}