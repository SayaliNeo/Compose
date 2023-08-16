package com.example.neospacecompose.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neospacecompose.R
import com.example.neospacecompose.repository.drawRating
import com.example.neospacecompose.ui.theme.Purple91
import com.example.neospacecompose.viewmodel.model.ProductViewModel
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun ProductDetailsScreen() {
    val ctx = LocalContext.current
    Log.d("Route","Product Screen")
    val productViewModel = ProductViewModel()
    Toast.makeText(ctx,"Product",Toast.LENGTH_SHORT).show()

    CardLayout(productViewModel)


}

@Preview
@Composable
fun productPreview() {
    ProductDetailsScreen()

}

@Composable
fun CardLayout(productViewModel: ProductViewModel) {
    LaunchedEffect(Unit, block = {
        productViewModel.allProductList()
    })
    val context = LocalContext.current
    val paddingModifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()

    val imageModifier = Modifier
        .size(120.dp)
        .padding(10.dp)

    Card(
        backgroundColor = Color.White, modifier = Modifier.fillMaxWidth(),
        elevation = 10.dp, shape = RoundedCornerShape(10.dp)) {

        if (productViewModel.errorMessage.isEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxHeight()){
                items(productViewModel.prodList) { list ->
                    Column {
                        Row {
                            Image(
                                painter = rememberGlidePainter(request = list.products[0].thumbnail),
                                contentDescription = "Product Image",
                                contentScale = ContentScale.Crop, modifier = Modifier
                                    .size(120.dp)
                                    .padding(10.dp)
                            )
                        }
                    }

                }

            }


        } else {
            Toast.makeText(
                context,
                "Unable to load data",
                Toast.LENGTH_LONG
            ).show()
        }

    }

}
/*  Column{
                                Row(modifier = Modifier.padding(top = 10.dp, start = 3.dp, end = 3.dp)) {
                                    Text(
                                        text = list.title,
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = stringResource(id = R.string.product_price) + " 175567",
                                        color = Purple91,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp, modifier = Modifier.weight(0.4f)
                                    )

                                }
                                Text(
                                    text = "SSIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 15.sp,
                                    overflow = TextOverflow.Ellipsis, maxLines = 2,
                                    modifier = Modifier.padding(3.dp)
                                )
                                Row() {
                                    RatingBar(rating = 3.7f, spaceBetween = 2.dp)
                                }
                            }*/

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp
) {

    val image = ImageBitmap.imageResource(id = R.drawable.star_half)
    val imageFull = ImageBitmap.imageResource(id = R.drawable.star_full)

    val totalCount = 5

    val height = LocalDensity.current.run { image.height.toDp() }
    val width = LocalDensity.current.run { image.width.toDp() }
    val space = LocalDensity.current.run { spaceBetween.toPx() }
    val totalWidth = width * totalCount + spaceBetween * (totalCount - 1)


    Box(
        modifier
            .width(totalWidth)
            .height(height)
            .drawBehind {
                drawRating(rating, image, imageFull, space)
            })
}



