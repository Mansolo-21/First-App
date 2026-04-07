package com.nohari.noharishop.navigation

import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nohari.noharishop.screens.demo.login.LoginScreen
import com.nohari.noharishop.screens.splashscreen.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String =ROUTE_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_SPLASH){
            SplashScreen(navController)
        }
    }
}