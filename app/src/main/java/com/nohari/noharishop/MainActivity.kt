package com.nohari.noharishop

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nohari.noharishop.navigation.AppNavHost
import com.nohari.noharishop.screens.demo.demo.FirstScreen
import com.nohari.noharishop.screens.demo.login.LoginScreen
import com.nohari.noharishop.ui.theme.NohariShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost()
        }
    }
}

@Composable
fun demo(){
    Column(modifier= Modifier.fillMaxSize()
        .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text ="hello good afternoon",
            color = Color.Red,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text ="this is jetpack compose",
            color = Color.Red,
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive
        )
    }}

@Preview(showBackground = true)
@Composable
fun demopreview(){
    demo()
}


