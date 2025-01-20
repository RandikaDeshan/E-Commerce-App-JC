package com.example.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SuccessPage(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 47.dp).verticalScroll(
            rememberScrollState()
        )
    ) {
        IconButton(
            onClick = {navController.popBackStack()},
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(400.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().size(width = 500.dp , height = 450.dp).align(Alignment.Center)
            )
            Column(
                modifier = Modifier.align(Alignment.Center).padding(bottom = 120.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group6872),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().size(width = 116.dp, height = 116.dp)
                )
            }
        }
        Text(
            text = "Congrats! Your Order has been placed",
            fontSize = 24.sp,
            fontWeight = FontWeight(900),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.padding(20.dp))
        Text(
            text = "Your items has been placed and is on it's way to being processed",
            fontSize = 14.sp,
            fontWeight = FontWeight(900),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.padding(20.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DeliveryDining,
                    contentDescription = ""
                )
                Spacer(Modifier.padding(20.dp))
                Text(
                    text = "TRACK ORDER",
                    fontSize = 11.sp,
                    fontWeight = FontWeight(900)
                )
            }
        }
        Spacer(Modifier.padding(16.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
                Spacer(Modifier.padding(20.dp))
                Text(
                    text = "CONTINUE SHOPPING",
                    fontSize = 11.sp,
                    fontWeight = FontWeight(900)
                )
            }
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 19.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            IconButton(
                onClick = {navController.navigate("home_screen")}
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
            Text(
                text = "Back to home",
                fontSize = 12.sp,
                fontWeight = FontWeight(900)
            )
        }
    }
}