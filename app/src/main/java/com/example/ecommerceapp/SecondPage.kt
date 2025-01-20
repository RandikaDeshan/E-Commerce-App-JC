package com.example.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SecondPage(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Image(
            painter = painterResource(R.drawable.second),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
            alpha = 0.1f
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "Geeta.",
                color = Color.Black,
                fontSize = 44.sp,
                fontWeight = FontWeight(900),
                modifier = Modifier.padding(top = 117.dp)
            )
            Text(
                text = "Create your fashion \n" +
                        "in your own way",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight(900),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 110.dp)
            )
            Text(
                text = "Each men and women has their own style, Geeta help you to create your unique style.",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 32.dp, start = 50.dp, end = 50.dp)
            )
            Button(
                onClick = {
                    navController.navigate("sign_in") {
                        popUpTo(0)
                    }
                },
                modifier = Modifier.padding(bottom = 13.dp, top = 49.dp).size(width = 222.dp, height = 54.dp).border(2.dp, Color.Black ,shape = RoundedCornerShape(56.dp) ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "LOG IN",
                    color = Color.Black,
                    fontWeight = FontWeight(900),
                    fontSize = 11.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 21.dp, height = 1.dp)
                        .background(Color.Black).align(Alignment.CenterVertically)
                )
                Text(
                    text = "OR",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )
                Box(
                    modifier = Modifier
                        .size(width = 21.dp, height = 1.dp)
                        .background(Color.Black).align(Alignment.CenterVertically)
                )
            }
            Button(
                onClick = { navController.navigate("sign_up") {
                    popUpTo(0)
                } },
                modifier = Modifier.padding(top = 13.dp).size(width = 222.dp, height = 54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    text = "REGISTER",
                    color = Color.White,
                    fontWeight = FontWeight(900),
                    fontSize = 11.sp
                )
            }
        }
    }
}

