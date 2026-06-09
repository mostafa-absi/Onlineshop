package com.lib.Onlineshop.ViewModel

import android.R
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.Onlineshop.base.ConnectivityObserver
import com.lib.Onlineshop.data.ds.ProfileManager
import com.lib.Onlineshop.repository.LoginRepository
import com.lib.Onlineshop.repository.ProductRepository
import com.lib.Onlineshop.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val profileManager: ProfileManager,
    val internetLoginObserver: ConnectivityObserver

) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state = _state.asStateFlow()

        val password = profileManager.getPassword()
            .stateIn(viewModelScope, SharingStarted.Eagerly, "")


    fun sendLoginData(username: String, password: String) {

        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val result = loginRepository.login(username, password)
                profileManager.saveToken(result.token)
                profileManager.savePassword(password)
                _state.value = LoginState.Success(result.token)
            } catch (e: IOException) {
                _state.value = LoginState.Error("No internet connection")
            } catch (e: Exception) {
                _state.value = LoginState.Error("Login failed")
            }
        }
    }
    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val token: String) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
