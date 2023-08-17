package com.example.neospacecompose.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun ProductViewDetailsScreen(navController: NavHostController) {
    Toast.makeText(LocalContext.current,"In View Screen",Toast.LENGTH_LONG).show()

}