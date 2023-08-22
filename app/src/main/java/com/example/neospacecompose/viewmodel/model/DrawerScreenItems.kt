package com.example.neospacecompose.viewmodel.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.neospacecompose.presentation.navigation.MovieScreens

sealed class DrawerScreenItems(
    val route:String,
    val title: String,
    val icons: ImageVector?=null
){
object Home: DrawerScreenItems("Home","Home", Icons.Filled.Home)
object Products: DrawerScreenItems("Products","Products", Icons.Rounded.ShoppingCart)
object MyAccount: DrawerScreenItems("Account","My Account", Icons.Filled.Person)
object Logout: DrawerScreenItems("Logout","Logout", Icons.Filled.ArrowBack)
//object ProductDetailsViewScreen: DrawerScreenItems("ProductViewById/{title}","ViewProduct")
object ProductDetailsViewScreen: DrawerScreenItems("ProductViewById","ViewProduct")
}

/*
enum class DrawerScreenItems{
    Home,ProductDetailsScreen;
    companion object{
        fun fromRoute(route:String): MovieScreens
                = when(route?.substringBefore("/")){
            Hom.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}*/
