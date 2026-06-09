package com.lib.Onlineshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.data.database.dao.productDao


@Database(
    entities = [ProductModel::class],
    version = 2
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun productDao(): productDao
}