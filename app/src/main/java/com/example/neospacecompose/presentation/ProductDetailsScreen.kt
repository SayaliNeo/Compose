package com.example.neospacecompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neospacecompose.R
import com.example.neospacecompose.model.Products
import com.example.neospacecompose.repository.drawRating
import com.example.neospacecompose.ui.theme.CardBackground
import com.example.neospacecompose.ui.theme.Purple91
import com.example.neospacecompose.viewmodel.model.DrawerScreenItems
import com.example.neospacecompose.viewmodel.model.ProductViewModel
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun ProductDetailsScreen(productViewModel: ProductViewModel, navController: NavHostController) {
    // LaunchedEffect(Unit, block = {
    productViewModel.allProductList()
    //})

    CardLayout(productViewModel = productViewModel, navController){ i: Int, productsList: List<Products> -> }


//    CardLayout(productViewModel=productViewModel){title->
//        navController.navigate(DrawerScreenItems.DetailsViewScreen.route)
//    }
}


@Composable
fun CardLayout(productViewModel: ProductViewModel, navController: NavHostController,onProductItemClick : (Int,List<Products>) -> Unit) {

    val context = LocalContext.current

    if (productViewModel.prodList.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(productViewModel.prodList) { list ->
                Card(
                    backgroundColor = CardBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            list.id?.let { onProductItemClick.invoke(it,
                                listOf(productViewModel.prodList[it])
                            )}
                           // navController.navigate(Screen.MyDetailScreen.route+"/${mylist.something}"
                            //navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route+"/${list.title}")
                            //navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route+"/${navController.currentBackStackEntry?.arguments?.putSerializable("products",list)}")
                            navController.currentBackStackEntry
                                ?.arguments?.putSerializable("products", list)
                            navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route)
                        },

                                             /*{
                            navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route){

                            }*/
                    /* .clickable { navController.navigate(DrawerScreenItems.ProductDetailsViewScreen.route)},*/
                    elevation = 5.dp,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column {
                        Row {
                            Image(
                                painter = rememberGlidePainter(request = list.thumbnail),
                                contentDescription = "Product Image",
                                contentScale = ContentScale.Crop, modifier = Modifier
                                    .size(120.dp)
                                    .padding(10.dp)
                            )
                            Column {
                                Row(
                                    modifier = Modifier.padding(
                                        top = 10.dp,
                                        start = 3.dp,
                                        end = 3.dp
                                    )
                                ) {
                                    list.title?.let {
                                        Text(
                                            text = it,
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )
                                    }
                                    list.price?.let {
                                        Text(
                                            text = stringResource(id = R.string.product_price) + it,
                                            color = Purple91,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 15.sp,
                                            modifier = Modifier.weight(0.4f)
                                        )
                                    }

                                }
                                list.description?.let {
                                    Text(
                                        text = it,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 15.sp,
                                        overflow = TextOverflow.Ellipsis, maxLines = 2,
                                        modifier = Modifier.padding(3.dp)
                                    )
                                }
                                Row(modifier = Modifier.padding(top = 3.dp)) {
                                    list.rating?.toFloat()
                                        ?.let {
                                            RatingBar(
                                                rating = it,
                                                spaceBetween = 2.dp
                                            )
                                        }
                                }
                            }


                        }
                    }
                }


            }

        }


    } else {
        /* Toast.makeText(
             context,
             "Unable to load data",
             Toast.LENGTH_SHORT
         ).show()*/
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
            })
}



