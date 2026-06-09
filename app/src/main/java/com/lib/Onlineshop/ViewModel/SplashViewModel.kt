package com.lib.Onlineshop.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.Onlineshop.base.ConnectivityObserver
import com.lib.Onlineshop.data.ds.ProfileManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


//check user login and Internet Status
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val profileManager: ProfileManager,
    val observer: ConnectivityObserver
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        checkToken()
    }

    //it's just for test - in the other side most be checked and validate JWT token
    private fun checkToken() {
        viewModelScope.launch {
            delay(2000)
            val token = profileManager.getToken().first()
            _isLoggedIn.value = !token.isNullOrEmpty()
        }
    }

    fun retry(){
        checkToken()
    }
}
