package com.example.ecommerceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.data.model.CartProduct
import com.example.ecommerceapp.data.model.ProductItem
import com.example.ecommerceapp.presentation.CartViewModel
import com.example.ecommerceapp.presentation.ProductsViewModel
import com.google.gson.Gson
import kotlin.math.round

@Composable
fun ProductDetails(
    navController: NavController,
    productsViewModel: ProductsViewModel,
    productId: String,
    cartViewModel: CartViewModel
){

    val item = Gson().fromJson(productId,ProductItem::class.java)

    val sizes = listOf("S","M","L","XL","XXL")

    var size by remember {
        mutableStateOf("S")
    }

    var count by remember {
        mutableStateOf(1)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 69.dp, bottom = 25.dp, end = 30.dp, start = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = {
                    productsViewModel.clearSelectedProduct()
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            AsyncImage(
                model = item.image,
                contentDescription = "",
                modifier = Modifier
                    .size(width = 225.dp, height = 158.dp),
                contentScale = ContentScale.Fit
            )
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "FavoriteBorder",
                tint = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White)
                .padding(start = 30.dp, end = 30.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if(item.category == "men's clothing"){"Geeta Mens"}else if(item.category == "jewelery"){"Geeta Jewelery"}else if(item.category == "electronics"){"Geeta Electronics"}else{"Geeta Womens"},
                            fontSize = 13.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(400)
                        )

                        Text(
                            text = item.title.substring(startIndex = 0, endIndex = 10),
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(900),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            for(i in 1 until  6){
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "",
                                    modifier = Modifier.size(14.dp),
                                    tint = if(i<= round(item.rating.rate)){Color.Yellow}else{Color.Gray}
                                )
                        }
                            Text(
                                text = "( ${item.rating.rate.toString()} )",
                                fontSize = 11.sp,
                                fontWeight = FontWeight(400),
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }

                    }
                    Text(
                        text = "\$${item.price} USD",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(900)
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 26.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                if(count > 1){
                                    count --
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(9.dp)
                            )
                        }
                        Text(
                            text = count.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(900)
                        )
                        IconButton(
                            onClick = {
                                count ++
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(9.dp)
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "DESCRIPTION",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = item.description,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight(500),
                )

                Text(
                    text = "SELECT SIZE",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(800),
                    modifier = Modifier.padding(top = 30.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    sizes.forEach { it ->
                        SizeButton(
                            title = size,
                            buttonSize = it,
                            onClicked = {size = it}
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 65.dp, bottom = 40.dp),
                onClick = {
                     cartViewModel.addToCart(CartProduct(
                         id = item.id,
                         quantity = count,
                         name = item.title,
                         image = item.image,
                         price = item.price
                     ),count)
                    navController.navigate("cart_page")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        text =  "ADD TO Cart",
                        fontSize = 11.sp,
                        color = Color.White,
                        fontWeight = FontWeight(900),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SizeButton(buttonSize : String, title : String, onClicked : (String)->Unit){
    val isSelected = buttonSize == title
    val bgColor = if(isSelected){Color.Blue}else{Color.LightGray}
    val textColor = if(isSelected){Color.White}else{Color.Black}
    Box(
        modifier = Modifier.padding(end = 10.dp)
    ){
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 48.dp)
                .clip(RoundedCornerShape(6))
                .background(bgColor)
                .clickable(onClick = { onClicked(buttonSize) }),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = buttonSize,
                color = textColor
            )
        }
    }
}