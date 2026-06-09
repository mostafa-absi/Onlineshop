package com.lib.Onlineshop.repository

import com.lib.Onlineshop.data.Model.AddressModel
import com.lib.Onlineshop.data.ds.AddressManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepository @Inject constructor(
    private val addressManager: AddressManager
) {

    fun getAddresses() =
        addressManager.getAddresses()

    suspend fun addAddress(
        address: AddressModel
    ) {

        val current =
            addressManager.getAddresses().first()

        addressManager.saveAddresses(
            current + address
        )
    }

    suspend fun deleteAddress(
        id: String
    ) {

        val current =
            addressManager.getAddresses().first()

        addressManager.saveAddresses(
            current.filterNot {
                it.id == id
            }
        )
    }

    suspend fun updateAddress(
        address: AddressModel
    ) {

        val current =
            addressManager.getAddresses().first()

        val updated =
            current.map {

                if (it.id == address.id)
                    address
                else
                    it
            }

        addressManager.saveAddresses(updated)
    }

    suspend fun setDefaultAddress(
        id: String
    ) {
        val current = addressManager.getAddresses().first()

        val updated =current.map {
                it.copy(
                    isDefault = it.id == id
                )
            }

        addressManager.saveAddresses(updated)
    }
}