package com.lib.Onlineshop.ui.screens.Root

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.navigation.SetupNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ContextCastToActivity")
@Composable

// Design =  Shared Scaffold
fun RootUi() {

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute !in Navigation.hideRoute) {
                TopBar(navController)
            }
        },
        bottomBar = {
            if (currentRoute !in Navigation.hideRoute) {
                BottomBar(navController)
            }
        }

    ) { padding ->
        SetupNavigation(
            navController = navController,
            paddingValues = padding)
    }
}




