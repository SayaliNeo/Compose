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
import com.example.neospacecompose.viewmodel.model.ProductViewModel

@Composable
fun NavigationControl(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DrawerScreenItems.Products.route
    ) {
        //NavGrapghs
        composable(DrawerScreenItems.Home.route) {
            HomeScreen(navController)
        }
        //   https://dummyjson.com/products
        composable(DrawerScreenItems.Products.route) {
            ProductDetailsScreen(ProductViewModel(), navController)
        }
        /* composable(
             DrawerScreenItems.ProductDetailsViewScreen.route + "/{product}", arguments = listOf(
                 navArgument("product") {
                     type =  NavType.SerializableType(Products::class.java)
                 })
         ) { backStackEntry ->
             val userJson = backStackEntry.arguments?.getString("product")

             val moshi = Moshi.Builder()
                 .add(KotlinJsonAdapterFactory()).build()
             val jsonAdapter = moshi.adapter(Products::class.java).lenient()
             ProductViewDetailsScreen(navController,jsonAdapter.fromJson(userJson))
         }*/
//https://dummyjson.com/products/id=2
        composable(
            DrawerScreenItems.ProductDetailsViewScreen.route+"/{id}", arguments = listOf(navArgument("id"){type = NavType.IntType})
        ) {backStackEntry ->
            ProductViewDetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getInt("id")
            )
        }
    }
}


