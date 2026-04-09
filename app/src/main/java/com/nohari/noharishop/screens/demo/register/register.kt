import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohari.noharishop.R
import com.nohari.noharishop.navigation.ROUTE_LOGIN
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.nohari.noharishop.data.AuthViewModel

@Composable
fun RegisterScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        var fullname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmation by remember { mutableStateOf("") }

        Text(
            text = "Register",
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id=R.drawable.logo),
            contentDescription = "Company Logo",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
            // .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Full Name") },
            singleLine = true,
            modifier = Modifier.padding(top = 16.dp),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription="Profile icon",

                    ) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.padding(top = 8.dp),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription="email icon",

                    ) },
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.padding(top = 8.dp),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription="password icon",

                    ) },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = confirmation,
            onValueChange = { confirmation = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier.padding(top = 8.dp),
            leadingIcon ={
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription="password icon",

                    ) },

        )
        Spacer(modifier = Modifier.height(40.dp))
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(
            onClick = {
                Log.d("DEBUG_EMAIL", "Email: '$email'")
                authViewModel.signup(
                    fullname.trim(),
                    email.trim(),
                    password.trim(),
                    confirmation.trim()
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            )
        ) {
            Text("Register", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))


        TextButton(onClick = {navController.navigate(ROUTE_LOGIN)}) {
            Text("Already have an account? Login")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Registerpreview(){
    RegisterScreen(rememberNavController())
}