package com.example.ecommerceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CartPage(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 30.dp)
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
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            content = {
                items(100, itemContent = {
                    CartItem()
                })
            }
        )
    }
}

@Composable
fun CartItem(){
    Card (
        modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = 10.dp)
    ){
        Row {
            Column {
                Text(
                    text = "Printed Shirt",
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
                    text = "\$28.00 USD",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                )
            }
        }
    }
}