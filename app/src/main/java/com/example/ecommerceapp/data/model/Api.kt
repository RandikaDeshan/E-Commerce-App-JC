package com.example.ecommerceapp.data.model

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("products")
    suspend fun getProducts(): List<ProductItem>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<ProductItem>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductItem
}