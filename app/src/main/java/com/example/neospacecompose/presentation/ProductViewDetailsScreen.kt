package com.example.neospacecompose.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.neospacecompose.model.Products

@Composable
fun ProductViewDetailsScreen(navController: NavHostController, product: Products?) {
   product?.title?.let { Text(text = it) }

}