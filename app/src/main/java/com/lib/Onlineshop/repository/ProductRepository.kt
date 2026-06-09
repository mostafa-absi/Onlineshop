package com.lib.Onlineshop.repository

import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.data.database.dao.productDao
import com.lib.Onlineshop.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: productDao
) {

    fun observeProducts(): Flow<List<ProductModel>> {
        return productDao.getAllProducts()
    }

    suspend fun syncProducts() {

        val local =
            productDao.getAllProductsOnce()

        if (local.isNotEmpty()) {
            return
        }

        val remote =
            apiService.getProducts()

        productDao.insertProducts(remote)
    }



    suspend fun toggleFavorite(
        productId: Int
    ) {
        val product =
            productDao.getProductById(productId)
                ?: return

        productDao.updateProduct(
            product.copy(
                isFavorite =
                    !product.isFavorite
            )
        )
    }


    suspend fun addToCart(
        productId: Int
    ) {

        val product =
            productDao.getProductById(productId)
                ?: return

        productDao.updateProduct(
            product.copy(
                quantity =
                    product.quantity + 1
            )
        )
    }

    suspend fun removeFromCart(
        productId: Int
    ) {

        val product =
            productDao.getProductById(productId)
                ?: return

        productDao.updateProduct(
            product.copy(
                quantity =
                    maxOf(
                        0,
                        product.quantity - 1
                    )
            )
        )
    }
}