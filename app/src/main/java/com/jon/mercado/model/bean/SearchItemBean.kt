package com.jon.mercado.model.bean

data class SearchItemBean(
    val id: String,
    val title: String,
    val price: Float,
    val thumbnail: String,
    val shipping: ShippingBean?,
    val installments: InstallmentsBean?
)

data class ShippingBean(
    val free_shipping: Boolean
)

data class InstallmentsBean(
    val quantity: Int,
    val amount: Float
)