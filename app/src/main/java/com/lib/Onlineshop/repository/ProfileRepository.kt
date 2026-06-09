package com.lib.Onlineshop.repository

import com.lib.Onlineshop.data.Model.UserProfileModel
import com.lib.Onlineshop.data.ds.ProfileManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileManager: ProfileManager
) {

    fun getProfile(): Flow<UserProfileModel> {
        return profileManager.getProfile()
    }
    suspend fun saveProfile(
        name: String,
        phone: String,
        email: String,
        birth: String,
        gender: String
    ) {
        profileManager.saveProfile(name, phone, email, birth, gender)
    }

    suspend fun clear() {
        profileManager.clear()
    }
}