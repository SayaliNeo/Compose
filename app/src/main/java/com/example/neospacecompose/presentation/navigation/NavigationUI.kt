package com.example.neospacecompose.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.neospacecompose.R
import com.example.neospacecompose.ui.theme.LightBackground
import com.example.neospacecompose.ui.theme.Purple71
import com.example.neospacecompose.viewmodel.model.DrawerScreenItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    title: String,
    imageVector: ImageVector? = null
) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 16.sp)
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(Icons.Filled.Menu, "")

            }
        },
        backgroundColor = Purple71,
        contentColor = Color.White,
        actions = { imageVector?.let { Icon(imageVector = it, contentDescription = "Icon") } }
    )

}

/*@Composable
@Preview
fun DisplayPreview() {
    //TopBar(scope = rememberCoroutineScope(), scaffoldState = rememberScaffoldState())
    Drawer(
        scope = rememberCoroutineScope(),
        scaffoldState = rememberScaffoldState(), navController = rememberNavController()
    )
}*/


@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController, title: (String) -> Unit) {
    val items = listOf(
        DrawerScreenItems.Home, DrawerScreenItems.Products,
        DrawerScreenItems.MyAccount, DrawerScreenItems.Logout, DrawerScreenItems.Notes
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column() {
        DrawerHeader()
        items.forEach { items ->
            DrawerItemList(
                items = items, selected = currentRoute == items.route,
                onItemClick = {
                    navController.navigate(items.route) {
                        title.invoke(items.title)
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    scope.launch { scaffoldState.drawerState.close() }
                }


            )
        }
    }


}

@Composable
fun DrawerItemList(
    items: DrawerScreenItems,
    selected: Boolean,
    onItemClick: (DrawerScreenItems) -> Unit
) {
    val drawerModifier = Modifier
        .fillMaxWidth()
        .height(45.dp)
        .clickable { onItemClick(items) }
        .background(color = Color.White)
        .padding(start = 10.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = drawerModifier
    ) {

        items.icons?.let { Image(imageVector = it, contentDescription = items.title) }
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = items.title,
            fontSize = 18.sp,
            color = Color.Black, fontWeight = FontWeight.Medium
        )
    }


}

@Composable
fun DrawerHeader() {
    val imageModifier = Modifier
        .size(120.dp)
        .padding(top = 30.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Fit, modifier = imageModifier
        )
        Text(
            "John Jacob",
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp), fontSize = 18.sp
        )
        Text(
            "john@gmail.com",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 3.dp, bottom = 20.dp),
            fontSize = 15.sp
        )


    }
}


