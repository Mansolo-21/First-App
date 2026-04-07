package com.nohari.noharishop.screens.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohari.noharishop.R

@Composable
fun MyCardExample(){
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red,
            contentColor = Color.Black,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(){
            Image(
                painter = painterResource(id=R.drawable.logo),
                contentDescription = "lOGO",
                modifier = Modifier.fillMaxWidth())
        Text("Hello")}
    }
}
@Preview
@Composable
fun mycardpreview(){
    MyCardExample()
}