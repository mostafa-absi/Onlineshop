package com.lib.Onlineshop.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.Onlineshop.base.ConnectivityObserver
import com.lib.Onlineshop.data.Model.UserProfileModel
import com.lib.Onlineshop.repository.LoginRepository
import com.lib.Onlineshop.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

//check username - password and internet

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    // -------- GET PROFILE --------
    val profile = profileRepository.getProfile()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfileModel())


    fun saveProfile(
        data: UserProfileModel
    ) {
        viewModelScope.launch {
            profileRepository.saveProfile(data.name, data.phone, data.email, data.birth, data.gender)
        }
    }

    fun logout() {
        viewModelScope.launch {
            profileRepository.clear()
        }
    }

}


