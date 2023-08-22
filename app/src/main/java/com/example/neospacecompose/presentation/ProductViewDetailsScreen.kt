package com.example.neospacecompose.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun ProductViewDetailsScreen(navController: NavHostController, id: Int?) {
    /*product?.fromJson("product")?.title?.let { Text(text = it) }
    product?.title?.let { Text(text = it) }*/

    id?.let { Text(it.toString()) }
    Column() {
        val images = listOf(
            "https://i.dummyjson.com/data/products/2/1.jpg",
            "https://i.dummyjson.com/data/products/2/2.jpg",
            "https://i.dummyjson.com/data/products/2/3.jpg",
            "https://i.dummyjson.com/data/products/2/thumbnail.jpg"
        )

        ImageSlider(images)
    }
}

@Composable
fun ImageSlider(images: List<Any>) {
    var currentImageIndex by remember { mutableStateOf(0) }
    var isAnimating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(100.dp)
            .weight(1f)) {
            LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                itemsIndexed(images){index, item ->
                    Card(modifier = Modifier
                        .width(250.dp)
                        .height(200.dp)) {
                       NetworkImage(contentDescription = "",
                           image = item as String,
                           width = 300,
                           height = 200)
                    }
                }
            }

        }
    }




    // Automatic Image Slider
    LaunchedEffect(currentImageIndex) {
        while (true) {
            delay(5000L)
            if (!isAnimating) {
                val nextIndex = (currentImageIndex + 1) % images.size
                currentImageIndex = nextIndex
            }
        }
    }
}

@Composable
fun NetworkImage(contentDescription: String, image: String, width: Int, height: Int) {
    val painter:Painter = rememberAsyncImagePainter(image)
    AsyncImage(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp),
        model = image,
        contentDescription = "Translated description of what the image contains"
    )
}

@Preview
@Composable
fun defaultPreview() {
    Column() {
        val images = listOf(
            "https://i.dummyjson.com/data/products/2/1.jpg",
            "https://i.dummyjson.com/data/products/2/2.jpg",
            "https://i.dummyjson.com/data/products/2/3.jpg",
            "https://i.dummyjson.com/data/products/2/thumbnail.jpg"
        )

        ImageSlider(images)
    }
}

