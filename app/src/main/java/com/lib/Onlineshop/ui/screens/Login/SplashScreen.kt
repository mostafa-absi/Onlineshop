package com.lib.Onlineshop.ui.screens.Login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled._360
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.SplashViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.theme.OSGrayText

@SuppressLint("ContextCastToActivity")
@Composable
fun SplashView(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val netState by viewModel.observer.online.collectAsState(true)
    val context = LocalContext.current

    // Handle splash navigation after network and auth checks
    LaunchedEffect(isLoggedIn, netState) {
        if (!netState) {
            return@LaunchedEffect
        }
        if (isLoggedIn == null) {
            return@LaunchedEffect
        }
        if (isLoggedIn == true) {
            navController.navigate(Navigation.Home.route) {
                popUpTo(Navigation.Splash.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Navigation.Login.route) {
                popUpTo(Navigation.Splash.route) {
                    inclusive = true
                }
            }
        }
    }

        SplashAndAuthBackground {
        Image(
            painter = painterResource(R.drawable.onlineshoplogo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(Modifier.height(16.dp))

        // Show loading state or allow retry when offline
        if (netState) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = OSGrayText,
                strokeWidth = 2.dp
            )
        } else {
            IconButton(onClick = {
                Toast.makeText(context, "لطفا اتصال اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show()
                viewModel.retry()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled._360,contentDescription = "Retry")
            }
        }

    }}



