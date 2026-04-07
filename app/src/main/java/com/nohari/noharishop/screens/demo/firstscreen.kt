package com.nohari.noharishop.screens.demo.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohari.noharishop.R
import kotlinx.coroutines.sync.Mutex

@Composable
fun FirstScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
            Text(
                text = "welcome to my app",
                fontSize = 24.sp,
                color = Color.Blue,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id=R.drawable.logo),
                contentDescription = "Company Logo",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                // .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(40.dp))
            //row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                //two buttons
                Button(onClick = {}) {
                    Text("Login")
                }
                Button(onClick = {}) {
                    Text("Register")
                }
            }

        }
}
@Preview(showBackground = true)
@Composable
fun firstscreenpreview(){
    FirstScreen()
}

