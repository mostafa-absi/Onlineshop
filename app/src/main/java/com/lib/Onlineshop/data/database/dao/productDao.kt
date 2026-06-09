package com.lib.Onlineshop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lib.Onlineshop.data.Model.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface productDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(
        products: List<ProductModel>
    )

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductModel>>

    @Query("SELECT * FROM products")
    suspend fun getAllProductsOnce(): List<ProductModel>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(
        id: Int
    ): ProductModel?

    @Update
    suspend fun updateProduct(
        product: ProductModel
    )

    }