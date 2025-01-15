package com.example.ecommerceapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.Repo
import com.example.ecommerceapp.data.model.ProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProductsViewModel: ViewModel() {
    private val repo = Repo()

    private val _products = MutableStateFlow<List<ProductItem>>(emptyList())
    val products : StateFlow<List<ProductItem>> = _products

    private val _selectedProduct = MutableStateFlow<ProductItem?>(null)
    val selectedProduct: StateFlow<ProductItem?> = _selectedProduct

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val productList = repo.getAllProducts()
                _products.value = productList
                _categories.value = productList.map { it.category }.distinct()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _products.value = repo.getProductsByCategory(category)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _selectedProduct.value = repo.getProductById(id)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSelectedProduct() {
        _selectedProduct.value = null
    }
}