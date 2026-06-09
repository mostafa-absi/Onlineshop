package com.lib.Onlineshop.data.ds

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lib.Onlineshop.data.Model.AddressModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dataStore = context.dataStore

    companion object {
        private val ADDRESS_LIST_KEY = stringPreferencesKey("address_list")
    }

    // Make Address to JSON model
    private val gson = Gson()

    suspend fun saveAddresses(
        addresses: List<AddressModel>
    ) {
        val json = gson.toJson(addresses)

        dataStore.edit {
            it[ADDRESS_LIST_KEY] = json
        }
    }

    fun getAddresses(): Flow<List<AddressModel>> {

        return dataStore.data.map { prefs ->

            val json =
                prefs[ADDRESS_LIST_KEY] ?: "[]"

            val type =
                object : TypeToken<List<AddressModel>>() {}.type

            gson.fromJson(json, type)
        }
    }
}