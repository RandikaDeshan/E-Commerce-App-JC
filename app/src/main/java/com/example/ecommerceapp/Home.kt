package com.example.ecommerceapp

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.presentation.ProductsViewModel
import com.google.gson.Gson

@Composable
fun Home(navController: NavController, authViewModel: AuthService,productsViewModel: ProductsViewModel = viewModel()) {

    val authState = authViewModel.authState.observeAsState()
    val products by productsViewModel.products.collectAsState()
    val isLoading by productsViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.loadProducts()
    }

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("sign_in") {
                popUpTo(0)
            }
            else -> Unit
        }
    }


    val categories = listOf("Popular","Mens","Womens","Jewelery","Electronics")
    var selecteadItem by remember {
        mutableIntStateOf(0)
    }
    var listAndGridVies by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        categories.size
    }
    LaunchedEffect(selecteadItem) {
        pagerState.animateScrollToPage(selecteadItem)
        when (categories[selecteadItem]) {
            "Popular" -> productsViewModel.loadProducts()
            "Mens" -> productsViewModel.getProductsByCategory("men's clothing")
            "Womens" -> productsViewModel.getProductsByCategory("women's clothing")
            "Jewelery" -> productsViewModel.getProductsByCategory("jewelery")
            "Electronics" -> productsViewModel.getProductsByCategory("electronics")
            else -> productsViewModel.getProductsByCategory(categories[selecteadItem].lowercase())
        }
    }
    LaunchedEffect(pagerState.currentPage,pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress){
            selecteadItem = pagerState.currentPage
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White).padding(horizontal = 30.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(top = 68.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Geeta.",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(900),
                )
                Row {
                    IconButton(
                        onClick = { authViewModel.logout()}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "LogOut",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate("cart_page")}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "LogOut",
                            tint = Color.Black
                        )
                    }
                }

            }
            ScrollableTabRow(
                modifier = Modifier.padding(top = 59.dp),
                edgePadding = 10.dp,
                containerColor = Color.White,
                selectedTabIndex = selecteadItem,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selecteadItem]),
                        color = Color.Blue,
                        height = 2.dp
                    )
                },
                divider = {
                    Divider(
                        color = Color.Transparent,
                        thickness = 0.dp
                    )
                },
            ) {
                categories.forEachIndexed { index, category ->
                    Tab(
                        selected = selecteadItem == index,
                        onClick = {
                            selecteadItem = index
                        },
                    ) {
                        Text(
                            text = category,
                            color =if(selecteadItem == index){Color.Blue}else{ Color.Black},
                            fontSize = 17.sp,
                            fontWeight = FontWeight(800),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FILTER  & SORT",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                )
                Row {
                    IconButton(
                        onClick = {
                            listAndGridVies = 1
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ViewList,
                            contentDescription = "List",
                            tint = if(listAndGridVies == 1){Color.Blue}else{Color.Black}
                        )
                    }
                    IconButton(
                        onClick = {
                            listAndGridVies = 0
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.GridView,
                            contentDescription = "GridView",
                            tint = if(listAndGridVies == 0){Color.Blue}else{Color.Black}
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) { index ->
                if(isLoading){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.Blue)
                    }
                }
                else if(listAndGridVies == 0){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(products) { product ->
                            ProductCard(product = product, onClick = {
                                val productJson = Uri.encode(Gson().toJson(product))
                                navController.navigate("product_detail/$productJson")
                            })
                        }
                    }
                }
                else{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(products) { product ->
                            ProductCard(product = product, onClick = {
                                navController.navigate("product_detail/${product.id}")
                            })
                        }
                    }

                }
            }
        }
    }
}



