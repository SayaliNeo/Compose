package com.example.neospacecompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neospacecompose.presentation.HomeScreen
import com.example.neospacecompose.presentation.ProductDetailsScreen
import com.example.neospacecompose.viewmodel.model.DrawerScreenItems

@Composable
fun NavigationControl(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DrawerScreenItems.Home.id
    ) {
//        composable(MovieScreens.HomeScreen.name){
//            // pass the leading screen
//            HomeScreen(navController = navController)
//        }
        composable(DrawerScreenItems.Home.id) {
            HomeScreen()
        }
        composable(DrawerScreenItems.Products.id) {
            ProductDetailsScreen()
        }

    }
}