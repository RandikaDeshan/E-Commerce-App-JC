package com.example.ecommerceapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.model.CartProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartProduct>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    fun addToCart(product: CartProduct) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.id == product.id }

        if(existingItem != null){
            val updatedItems = currentItems.map { item ->
                if(item.id == product.id){
                    item.copy(quantity = item.quantity + 1)
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
                quantity = 1
            )
            currentItems.add(newItem)
            _cartItems.value = currentItems
        }
        calculateTotal()
    }

    fun incrementQuantity(itemId: Int) {
        val updatedItems = _cartItems.value.map { item ->
            if (item.id == itemId) {
                item.copy(quantity = item.quantity + 1)
            } else {
                item
            }
        }
        _cartItems.value = updatedItems
        calculateTotal()
    }

    fun decrementQuantity(itemId: Int) {
        val updatedItems = _cartItems.value.map { item ->
            if (item.id == itemId && item.quantity > 1) {
                item.copy(quantity = item.quantity - 1)
            } else {
                item
            }
        }
        _cartItems.value = updatedItems
        calculateTotal()
    }

    fun removeItem(itemId: Int) {
        _cartItems.value = _cartItems.value.filter { it.id != itemId }
        calculateTotal()
    }

    private fun calculateTotal() {
        _totalPrice.value = _cartItems.value.sumOf { it.price * it.quantity }
    }

    // Optional: Get cart item count for badge
    val cartItemCount: StateFlow<Int> = _cartItems.map { items ->
        items.sumOf { it.quantity }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        0
    )


}