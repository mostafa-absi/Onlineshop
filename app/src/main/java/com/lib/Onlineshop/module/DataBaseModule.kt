package com.lib.Onlineshop.module

import android.content.Context
import androidx.room.Room
import com.lib.Onlineshop.data.database.MyDatabase
import com.lib.Onlineshop.data.database.dao.productDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context1: Context
    ): MyDatabase{
        return Room.databaseBuilder(
            context =context1,
            klass = MyDatabase::class.java,
            name="shop_DB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(
        database: MyDatabase
    ): productDao {
        return database.productDao()
    }
}