package com.example.ecommerceapp.data

sealed class PaymentStatus {
    object Idle : PaymentStatus()
    object Processing : PaymentStatus()
    object Success : PaymentStatus()
    data class Failed(val error: Throwable) : PaymentStatus()
    object Cancelled : PaymentStatus()
}