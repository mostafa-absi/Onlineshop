package com.lib.Onlineshop.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var allProducts = emptyList<ProductModel>()

    private val _products =
        MutableStateFlow<List<ProductModel>>(emptyList())
    val products: StateFlow<List<ProductModel>> = _products

    private val _favorites =
        MutableStateFlow<List<ProductModel>>(emptyList())
    val favorites: StateFlow<List<ProductModel>> = _favorites

    private val _cartProducts =
        MutableStateFlow<List<ProductModel>>(emptyList())
    val cartProducts: StateFlow<List<ProductModel>> = _cartProducts

    private val _message =
        MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    private val _isLoading =
        MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init { observeProducts()}

    private fun observeProducts() {

        viewModelScope.launch {

            try {

                productRepository.syncProducts()

                productRepository
                    .observeProducts()
                    .collect { products ->
                        allProducts = products
                        _products.value = products
                        _favorites.value =
                            products.filter {
                                it.isFavorite
                            }
                        _cartProducts.value =
                            products.filter {
                                it.quantity > 0
                            }
                        _isLoading.value = false
                    }

             } catch (e: IOException) {

                _message.emit("No internet connection")
                _isLoading.value = false

            } catch (e: Exception) {

                _message.emit(e.message ?: "Server error")
                _isLoading.value = false
            }
        }
    }

    fun search(
        query: String
    ) {
        _products.value =
            if (query.isBlank()) { allProducts
            } else {
                allProducts.filter {
                    it.title.contains(
                        query,
                        ignoreCase = true
                    )
                }
            }
    }

    fun toggleFavorite(
        productId: Int
    ) {
        viewModelScope.launch {

            productRepository
                .toggleFavorite(productId)
        }
    }


    fun addToCart(
        productId: Int
    ) {

        viewModelScope.launch {
            productRepository.addToCart(productId)
        }
    }

    fun removeFromCart(
        productId: Int
    ) {

        viewModelScope.launch {

            productRepository
                .removeFromCart(productId)
        }
    }

    fun clearCart() {

        viewModelScope.launch {

            cartProducts.value.forEach { product ->

                repeat(product.quantity) {

                    productRepository
                        .removeFromCart(product.id)
                }
            }
        }
    }

    fun getCartTotalPrice(): Double {

        return cartProducts.value.sumOf {

            it.price * it.quantity
        }
    }

    fun getCartItemsCount(): Int {

        return cartProducts.value.sumOf {

            it.quantity
        }
    }
}