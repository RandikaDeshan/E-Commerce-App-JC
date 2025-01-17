package com.example.ecommerceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.presentation.CartViewModel

@Composable
fun CartPage(navController: NavController,cartViewModel: CartViewModel){

    val cartItem by cartViewModel.cartItems.observeAsState(emptyList())
    val totalPrice by cartViewModel.totalPrice.observeAsState(0.0)


    println(cartItem)
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize().padding(top = 120.dp, bottom = 100.dp),
            content = {
                itemsIndexed(cartItem,
                    itemContent = {index,item ->
                    CartItem(
                        title = item.name,
                        price = item.price,
                        img = item.image,
                        quantity = item.quantity,
                        removeItem ={cartViewModel.decrementQuantity(item.id)},
                        addItem = {cartViewModel.incrementQuantity(item.id)},
                        remove = {cartViewModel.removeItem(item.id)}
                    )
                    }
                )
            }
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 71.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
                Text(
                    text = "My Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(900)
                )
                Text(
                    text = "  "
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Logout,
                        contentDescription = ""
                    )
                    Text(
                        text =  "GO TO CHECKOUT",
                        fontSize = 11.sp,
                        fontWeight = FontWeight(900),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Text(
                        text =  "\$${totalPrice.toFloat()}%",
                        fontSize = 11.sp,
                        fontWeight = FontWeight(900),
                        modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(Color.DarkGray).padding(vertical = 3.dp, horizontal = 5.dp)
                    )
                }
            }
        }

    }

}

@Composable
fun CartItem(
    title : String,
    img : String,
    price : Double,
    quantity : Int,
    addItem : () -> Unit ={},
    removeItem : () -> Unit ={},
    remove : () -> Unit ={}
){
    Card (
        modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = 10.dp, horizontal = 30.dp)
    ){
        Row(
            modifier = Modifier.fillMaxSize().padding(start = 34.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = img,
                contentDescription = "",
                modifier = Modifier.size(width = 61.dp, height = 61.dp)
            )
            Column {
                Text(
                    text = title.substring(startIndex = 0, endIndex = 10),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                    color = Color.Blue
                )
                Text(
                    text = "Geeta Collection",
                    fontSize = 9.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Gray
                )
                Text(
                    text = "\$$price",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = remove
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.size(11.dp),
                        tint = Color.Gray
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = removeItem
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "",
                            modifier = Modifier.size(9.dp)
                        )
                    }
                    Text(
                        text = quantity.toString() ,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(900)
                    )
                    IconButton(
                        onClick = addItem
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            modifier = Modifier.size(9.dp)
                        )
                    }
                }
            }
        }
    }
}