package com.nohari.noharishop.screens.demo.demo

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun IntentScreen(navController: NavHostController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context= LocalContext.current
        Text("INTENTS")
        Button(onClick = {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        }
        ) {Text("open camera")

        }
        Button(onClick = {
            val intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emobilis.ac.ke")
            )
            context.startActivity(intent)

        }) {
            Text("open websiste")
        }


    }


}
@Preview(showBackground = true)
@Composable
fun intentpreview(){
    IntentScreen(rememberNavController())
}