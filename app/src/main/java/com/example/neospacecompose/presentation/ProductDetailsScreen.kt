package com.example.neospacecompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.neospacecompose.R
import com.example.neospacecompose.components.ButtonContainer
import com.example.neospacecompose.components.DraggableThumbButton
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.drawRating
import com.example.neospacecompose.ui.theme.CardBackground
import com.example.neospacecompose.ui.theme.Purple91
import com.example.neospacecompose.viewmodel.model.DrawerScreenItems
import com.example.neospacecompose.viewmodel.model.ProductViewModel
import com.google.accompanist.glide.rememberGlidePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel(),
    onProductItemClick: (Int, List<Products>) -> Unit
) {
    val prodData by productViewModel.prodList.collectAsState(initial = emptyList())



    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        prodData.forEachIndexed { index, products ->

            Card(
                backgroundColor = CardBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp).clickable {
                        products.id?.let {
                        onProductItemClick.invoke(
                            it,
                            listOf(productViewModel.prodList.value[it])
                        )
                    }
                        navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route + "/${products.id}") },
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
            ) {
                Row {
                    Image(
                        painter = rememberGlidePainter(request = products.thumbnail),
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(50.dp)),
                    )
                    Column {
                        Row(
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 3.dp,
                                end = 3.dp
                            )
                        ) {
                            products.title?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.weight(1f),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                            products.price?.let {
                                Text(
                                    text = stringResource(id = R.string.product_price) + it,
                                    color = Purple91,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    modifier = Modifier.weight(0.4f)
                                )
                            }

                        }
                        products.description?.let {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                overflow = TextOverflow.Ellipsis, maxLines = 2,
                                modifier = Modifier.padding(3.dp)
                            )
                        }
                        Row(modifier = Modifier.padding(top = 3.dp)) {
                            products.rating?.toFloat()
                                ?.let {
                                    RatingBar(
                                        rating = it,
                                        spaceBetween = 2.dp
                                    )
                                }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 3.dp, end = 10.dp),
                                //.background(color = Color.White),
                            horizontalArrangement = Arrangement.End
                        ) {
                           Counter()
                        }
                    }


                }
            }
        }


    }
}

@Composable
fun Counter() {
    var count by remember {
        mutableStateOf(0)
    }
    //val count = +state{0}
        Row (horizontalArrangement = Arrangement.End){
            Text(text = "-", modifier = Modifier
                .padding(8.dp)
                .clickable { count-- }, color = Purple91
            )
            Text(text = "${count}", modifier = Modifier
                .padding(8.dp)//.clip(CircleShape)
                 , color = Purple91
            )
            Text(text = "+", modifier = Modifier
                .padding(8.dp)
                .clickable { count++ }, color = Purple91
            )
            //Button(text = "+", onClick = { count++ })
        }
}


@Composable
private fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(50.dp)
            .height(40.dp)
    ) {

        ButtonContainer(
            onValueDecreaseClick = { onValueDecreaseClick },
            onValueIncreaseClick = { onValueIncreaseClick },
            onValueClearClick = { onValueClearClick },
            modifier = Modifier
        )

        DraggableThumbButton(
            value = value,
            onClick = { onValueIncreaseClick },
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun NonlazyGrid(
    columns: Int,
    itemCount: Int,
    modifier: Modifier = Modifier,
    content: @Composable() (Int) -> Unit
) {
    Column(modifier = modifier) {
        var rows = (itemCount / columns)
        if (itemCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns

            Row {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (index < itemCount) {
                            content(index)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp
) {

    val image = ImageBitmap.imageResource(id = R.drawable.star_half_24)
    val imageFull = ImageBitmap.imageResource(id = R.drawable.star_full_24)

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
            }
    )
}



