package com.lib.Onlineshop.data.ds

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.lib.Onlineshop.data.Model.UserProfileModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("user_token")
        private val PASSWORD_KEY = stringPreferencesKey("user_password")

        private val NAME_KEY = stringPreferencesKey("name")
        private val PHONE_NUMBER_KEY = stringPreferencesKey("phoneNumber")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val BIRTH_KEY = stringPreferencesKey("birth")
        private val GENDER_KEY = stringPreferencesKey("gender")
    }

    private val dataStore = context.dataStore

    //it's just for test
    suspend fun savePassword(password: String) {
        dataStore.edit { it[PASSWORD_KEY] = password }
    }

    fun getPassword(): Flow<String?> {
        return dataStore.data.map { it[PASSWORD_KEY] }
    }

    //Save JWT token
    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    //Get JWT token
    fun getToken(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[TOKEN_KEY]
        }
    }

    //Save Profile
    suspend fun saveProfile(
        name: String,
        phoneNumber: String,
        email: String,
        birth: String,
        gender: String
    ) {
        dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[PHONE_NUMBER_KEY] = phoneNumber
            prefs[EMAIL_KEY] = email
            prefs[BIRTH_KEY] = birth
            prefs[GENDER_KEY] = gender
        }
    }


    //Get Profile
    fun getProfile(): Flow<UserProfileModel> {
        return dataStore.data.map { prefs ->
            UserProfileModel(
                name = prefs[NAME_KEY] ?: "",
                phone = prefs[PHONE_NUMBER_KEY] ?: "",
                email = prefs[EMAIL_KEY] ?: "",
                birth = prefs[BIRTH_KEY] ?: "",
                gender = prefs[GENDER_KEY] ?: ""
            )
        }
    }

    // 🗑 Clear all
    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}