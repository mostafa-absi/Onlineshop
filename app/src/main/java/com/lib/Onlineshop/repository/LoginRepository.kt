package com.lib.Onlineshop.repository

import com.lib.Onlineshop.data.Model.LoginToken
import com.lib.Onlineshop.data.ds.ProfileManager
import com.lib.Onlineshop.data.remote.ApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun login(
        username: String,
        password: String
    ): LoginToken {
        return apiService.login(
            username = username,
            password = password
        )
    }

}