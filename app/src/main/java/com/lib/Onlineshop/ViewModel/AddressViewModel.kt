package com.lib.Onlineshop.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.Onlineshop.data.Model.AddressModel
import com.lib.Onlineshop.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    val addresses =
        repository.getAddresses()
            .stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())

    fun addAddress(
        address: AddressModel
    ) {
        viewModelScope.launch {repository.addAddress(address)}
    }

    fun deleteAddress(
        id: String
    ) {
        viewModelScope.launch {repository.deleteAddress(id)}
    }

    fun setDefaultAddress(
        id: String
    ) {
        viewModelScope.launch {repository.setDefaultAddress(id)}
    }


    // not used in app
    fun updateAddress(
        address: AddressModel
    ) {
        viewModelScope.launch {repository.updateAddress(address)}
    }
}