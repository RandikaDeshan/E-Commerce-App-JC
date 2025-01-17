package com.example.ecommerceapp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.data.model.CartProduct


class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartProduct>>()
    val cartItems : LiveData<List<CartProduct>> = _cartItems

    private val _totalPrice = MutableLiveData(0.0)
    val totalPrice : LiveData<Double> = _totalPrice

    fun addToCart(product: CartProduct,count:Int) {
        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = currentItems.find { it.id == product.id }

        if(existingItem != null){
            val updatedItems = currentItems.map { item ->
                if(item.id == product.id){
                    item.copy(quantity = item.quantity + count)
                }else{
                    item
                }
            }
            _cartItems.value = updatedItems
        }else{
            val newItem = CartProduct(
                id = product.id,
                name = product.name,
                price = product.price,
                image = product.image,
                quantity = count
            )
            currentItems.add(newItem)
            println(newItem)
            _cartItems.value = currentItems
            println(_cartItems.value)
        }
        calculateTotal()
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun incrementQuantity(itemId: Int) {
        val updatedItems = _cartItems.value?.map { item ->
            if (item.id == itemId) {
                item.copy(quantity = item.quantity + 1)
            } else {
                item
            }
        }
        _cartItems.value = updatedItems
        calculateTotal()
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun decrementQuantity(itemId: Int) {
        val updatedItems = _cartItems.value?.map { item ->
            if (item.id == itemId && item.quantity > 1) {
                item.copy(quantity = item.quantity - 1)
            }
            else {
                item
            }
        }
        _cartItems.value = updatedItems
        calculateTotal()
    }

    fun removeItem(itemId: Int) {
        _cartItems.value = _cartItems.value?.filter { it.id != itemId }
        calculateTotal()
    }

    private fun calculateTotal() {
        val currentItems = _cartItems.value ?: emptyList() // Safely handle null
        _totalPrice.value = currentItems.sumOf { cartProduct ->
            cartProduct.price * cartProduct.quantity
        }
    }



}