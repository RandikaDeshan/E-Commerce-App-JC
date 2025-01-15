package com.example.ecommerceapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SplashPage(navController: NavController) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        AsyncImage(
            model = "https://s3-alpha-sig.figma.com/img/c12d/6b4c/e30595d05b3f5509eace87d2e161fa10?Expires=1737331200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=q4g7wTITU7y76xpQZ~bgmfFFmeTeLcE-bF8hO4nM~oTyR-sbGNgnwxxmTh895p1OANJb2Fotchwr6UoJhAbumAtwz9W1K7aGjku6b8dcLpP1hNr~Y-OqQOlU8IKt5OevY59GT-F-0jf1qYNyR-FYf9VJOIVb9LhtKCEFhlG4SuDRUQBHO0YNpOu~plsAGglIeuPIot~gEGWh67ts~-Csebjq8VWAcl6AqPSgWCIJzggdlth~3l8w6QpdotFuGa6NH6MtDzqCz4lZPTNgW7B5VVhYuFWgAEHixXKZqYVmlwIfNPs9d1mAGCGRMDBxWBWDbybSM9dVHDxczIkQZl0hBw__",
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Text(
                text = "Geeta.",
                color = Color.White,
                fontSize = 104.sp,
                fontWeight = FontWeight(900),
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    navController.navigate("second_screen"){
                        popUpTo(0)
                    }
                },
                modifier = Modifier.padding(top = 20.dp, bottom = 125.dp).size(width = 222.dp, height = 54.dp).border(3.dp, Color.White, shape = RoundedCornerShape(56.dp) ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "SHOP NOW",
                    color = Color.White,
                    fontWeight = FontWeight(900),
                    fontSize = 14.sp
                )
            }
        }
    }
}