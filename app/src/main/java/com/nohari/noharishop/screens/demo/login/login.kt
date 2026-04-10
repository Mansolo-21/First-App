package com.nohari.noharishop.screens.demo.login

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.nohari.noharishop.R
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohari.noharishop.data.AuthViewModel
import com.nohari.noharishop.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavHostController){
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

        ) {
        Text(
            text = "Login",
            color = Color.Black,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Already have an account? Login here",
            color = Color.Black

        )
        Spacer(modifier = Modifier.height(70.dp))
        Image(
            painter = painterResource(id=R.drawable.logo),
            contentDescription = "Company Logo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
            // .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(70.dp))
        var email by remember{ mutableStateOf("") }
        var password by remember{ mutableStateOf("")}
        OutlinedTextField(
            value=email,
            onValueChange = {email=it},
            label = {Text("Enter Email")},
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription="email icon",

                ) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value=password,
            onValueChange = {password=it},
            label = {Text("Enter Email password")},
                    leadingIcon ={
                    Icon(imageVector = Icons.Default.Lock,
                    contentDescription="Password icon",
                    ) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),

        )
        Spacer(modifier = Modifier.height(30.dp))
        val context= LocalContext.current
        val myauth = remember { AuthViewModel(navController, context) }
        Button(
            onClick = {myauth.login(email, password)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            )
        )
        {
            Text("Login", fontSize = 24.sp)
        }
        TextButton(onClick = {navController.navigate(ROUTE_REGISTER)}) {
            Text("Don't Have an account? Register")
        }

    }

}
@Preview(showBackground = true)
@Composable
fun loginscreenpreview(){
    LoginScreen(navController= rememberNavController())
}