package com.example.ecommerceapp.data

import com.example.ecommerceapp.data.model.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repo {

    private val api = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    suspend fun getAllProducts() = api.getProducts()
    suspend fun getProductsByCategory(category: String) = api.getProductsByCategory(category)
    suspend fun getProductById(id: Int) = api.getProductById(id)
}