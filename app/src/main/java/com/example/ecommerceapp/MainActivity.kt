package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.presentation.CartViewModel
import com.example.ecommerceapp.presentation.ProductsViewModel
import com.example.ecommerceapp.ui.theme.ECommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val stateViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     NavigationPage(
                        authViewModel = AuthService(),
                         stateViewModel
                     )               }
            }
        }
    }
}
@Composable
fun NavigationPage(authViewModel: AuthService,stateViewModel:CartViewModel){
    val navController = rememberNavController()
    val authState = authViewModel.authState.observeAsState()


    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home_screen") {
                popUpTo(0)
            }
            else -> Unit
        }
    }

    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashPage(navController = navController)
        }
        composable("second_screen"){
            SecondPage(navController = navController)
        }
        composable("home_screen") {
            Home(navController = navController, authViewModel = authViewModel, productsViewModel = ProductsViewModel())
        }
        composable("sign_in") {
            SignInPage(navController = navController,authViewModel = authViewModel)
        }
        composable("sign_up") {
            SignUpPage(navController = navController,authViewModel = authViewModel)
        }
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) {backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetails(
                navController = navController,
                productsViewModel = ProductsViewModel(),
                productId = productId.toString(),
                cartViewModel = stateViewModel
                )
        }
        composable("cart_page") {
            CartPage(navController, cartViewModel = stateViewModel)
        }
    }
}

