package com.example.ecommerceapp.presentation

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.PaymentStatus
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StripeViewModel(application: Application) : AndroidViewModel(application) {
    private val _paymentStatus = MutableStateFlow<PaymentStatus>(PaymentStatus.Idle)
    val paymentStatus: StateFlow<PaymentStatus> = _paymentStatus

    private lateinit var paymentLauncher: PaymentLauncher

    init {
        PaymentConfiguration.init(application, "pk_test_51QcNscGXWoXihkxzNUCvN14FY17164FKhoNOg8lVjCnqsZoEwlPJLF7a51TVSpHHhFcLEXWcYCjVsfUZWVh6sqWm00rNOZ8xva")
    }

    fun initializePaymentLauncher(activity: ComponentActivity) {
        paymentLauncher = PaymentLauncher.create(
            stripeAccountId = null,
            activity = activity,
            callback = ::onPaymentResult,
            publishableKey = PaymentConfiguration.getInstance(activity).publishableKey,
//            this,
//            "pk_test_51QcNscGXWoXihkxzNUCvN14FY17164FKhoNOg8lVjCnqsZoEwlPJLF7a51TVSpHHhFcLEXWcYCjVsfUZWVh6sqWm00rNOZ8xva",
//            "sk_test_51QcNscGXWoXihkxzDAQv8VuZQez5NiJyK625oybuuNCdrcZHPjyRAvmrPVuWi5EpV1p9Z0yhqE1THvNpU0f39dy000XPXSSYMU",
//            ::onPaymentResult
        )
    }
    fun processPayment(clientSecret: String) {
        viewModelScope.launch {
            _paymentStatus.value = PaymentStatus.Processing
            val params = ConfirmPaymentIntentParams.createWithPaymentMethodId(
                paymentMethodId = "YOUR_PAYMENT_METHOD_ID",
                clientSecret = clientSecret
            )
            paymentLauncher.confirm(params)
        }
    }
    private fun onPaymentResult(paymentResult: PaymentResult) {
        when (paymentResult) {
            is PaymentResult.Completed -> {
                _paymentStatus.value = PaymentStatus.Success
            }
            is PaymentResult.Failed -> {
                _paymentStatus.value = PaymentStatus.Failed(paymentResult.throwable)
            }
            is PaymentResult.Canceled -> {
                _paymentStatus.value = PaymentStatus.Cancelled
            }
        }
    }
}