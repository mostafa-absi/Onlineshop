package com.lib.Onlineshop.data.Model

import java.util.UUID

data class AddressModel(
    val id: String = UUID.randomUUID().toString(),
    val receiverName: String = "",
    val address: String = "",
    val postalCode: String = "",
    val phoneNumber: String = "",
    val isDefault: Boolean = false
)