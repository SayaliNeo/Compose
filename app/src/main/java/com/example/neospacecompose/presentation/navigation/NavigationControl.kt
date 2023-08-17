package com.example.neospacecompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.neospacecompose.presentation.HomeScreen
import com.example.neospacecompose.presentation.ProductDetailsScreen
import com.example.neospacecompose.presentation.ProductViewDetailsScreen
import com.example.neospacecompose.viewmodel.model.DrawerScreenItems

@Composable
fun NavigationControl(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DrawerScreenItems.Products.route
    ) {
//        composable(MovieScreens.HomeScreen.name){
//            // pass the leading screen
//            HomeScreen(navController = navController)
//        }
        composable(DrawerScreenItems.Home.route) {
            HomeScreen()
        }
        composable(DrawerScreenItems.Products.route) {
            ProductDetailsScreen(navController)
        }
        composable(DrawerScreenItems.DetailsViewScreen.route,
        arguments = listOf(navArgument("title"){
            type = NavType.StringType
            defaultValue
        })
        ){
            ProductViewDetailsScreen(navController)
        }


    }
}


