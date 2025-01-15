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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.presentation.ProductsViewModel

@Composable
fun ProductDetails(
    navController: NavController,
    productsViewModel: ProductsViewModel,
    productId: Int?
){

    val product by productsViewModel.selectedProduct.collectAsState()
    val isLoading by productsViewModel.isLoading.collectAsState()

    LaunchedEffect(productId) {
        productId?.let { productsViewModel.getProductById(it) }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Text(
            text = "Product",
            modifier = Modifier.padding(top = 20.dp)
        )
        if(isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Blue)
            }
        }else{
            product?.let { productItem ->
                Column(
                modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    AsyncImage(
                        model = productItem.image,
                        contentDescription = productItem.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Text(
                    text = productItem.title
                )
            }
            }
    }
    }
}