@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.neospacecompose.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.neospacecompose.viewmodel.model.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val ctx = LocalContext.current
    Toast.makeText(ctx,"Home", Toast.LENGTH_SHORT).show()

    /* LaunchedEffect(Unit, block = {
        userViewModel.userDetailsList()
    })*/
    // MainContent(navController,userViewModel)
    /*    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Purple91, elevation = 0.dp) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Movies",
                    fontSize = 22.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        content = {
            CardLayout()
           *//* if (userViewModel.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(userViewModel.userList) { list ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Box(
                                        modifier = Modifier
                                           .fillMaxWidth()
                                            .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                    ) {
                                        Text(
                                            text = list.title, maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                    }

                                }
                            }

                        }
                    }
                }
            }*//*

        })

}*/
}



@Composable
fun previewMainContent() {
    val navController = rememberNavController()
    var userViewModel = UserViewModel()
    HomeScreen()
}