package com.nohari.noharishop.navigation

import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.nohari.noharishop.screens.dashboard.DashboardScreen
import com.nohari.noharishop.screens.demo.demo.IntentScreen
import com.nohari.noharishop.screens.demo.login.LoginScreen
import com.nohari.noharishop.screens.onboarding.OnboardingScreen
import com.nohari.noharishop.screens.products.AddProduct
import com.nohari.noharishop.screens.products.ProductListScreen
import com.nohari.noharishop.screens.products.UpdateProductScreen
import com.nohari.noharishop.screens.profile.ProfileScreen
import com.nohari.noharishop.screens.splashscreen.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // 🟣 SPLASH
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }

        // 🟢 ONBOARDING
        composable(ROUTE_ONBOARDING) {
            OnboardingScreen(navController)
        }

        // 🔵 AUTH
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        // 🏠 DASHBOARD
        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }

        // 📦 PRODUCTS
        composable(ROUTE_ADDPRODUCT) {
            AddProduct(navController)
        }

        composable(ROUTE_LISTPRODUCTS) {
            ProductListScreen(navController)
        }

        composable("$ROUTE_UPDATEPRODUCT/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""

            UpdateProductScreen(
                navController = navController,
                productId = productId
            )
        }

        // 📱 INTENT SCREEN
        composable(ROUTE_INTENT) {
            IntentScreen(navController)
        }

        // 👤 PROFILE
        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }
    }
}