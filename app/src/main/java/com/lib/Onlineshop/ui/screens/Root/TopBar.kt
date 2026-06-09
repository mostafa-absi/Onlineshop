package com.lib.Onlineshop.ui.screens.Root

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.ProfileViewModel
import com.lib.Onlineshop.data.Model.DropDownMenuItems
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.theme.OSLightOrang
import com.lib.Onlineshop.ui.theme.OSOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController
) {
    TopAppBar(
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),

        title = {

            //Black Color for Clock and Battry
            StatusBarColorItems()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArrowBack(navController)
                Image(
                    painter = painterResource(R.drawable.onlineshoplogo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .size(30.dp)
                )
                Text(
                    text = "Online",
                    fontWeight = FontWeight.SemiBold,
                    color = OSOrange
                )
                Text(
                    text = "Shop",
                    fontWeight = FontWeight.SemiBold
                )
            }
        },

        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Navigation.Profile.route) {
                        popUpTo(Navigation.Home.route)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {

                Image(
                    painter = painterResource(R.drawable.userpicsmall),
                    contentDescription = "Profile",
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = {
                navController.navigate(Navigation.Notification.route) {
                    popUpTo(Navigation.Home.route)
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Image(
                    painter = painterResource(R.drawable.bell),
                    "",
                    modifier = Modifier.size(20.dp)
                )
            }

            DropDownMenu(navController)
        }
    )
}

@Composable
fun ArrowBack(
    navController: NavHostController
) {
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    if (currentRoute !in Navigation.mainRoute) {

        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun StatusBarColorItems() {
    val window = (LocalContext.current as Activity).window
    SideEffect() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            true
    }

    //this function used for status bar color Item
}

@Composable
fun DropDownMenu(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val profile by profileViewModel.profile.collectAsState()
    var stateMenuExpand by remember { mutableStateOf(false) }
    IconButton({ stateMenuExpand = !stateMenuExpand }) {
        Icon(
            if (stateMenuExpand) {
                Icons.Default.Close
            } else {
                Icons.Default.Menu
            }, ""
        )
    }

    val dropDownMenuItem = listOf(
        DropDownMenuItems(
            name = "پروفایل کاربری",
            logo = R.drawable.profile,
            route = Navigation.Profile.route
        ),
        DropDownMenuItems(
            name = "سفارشات من",
            logo = R.drawable.orders,
            route = Navigation.Cart.route
        ),
        DropDownMenuItems(
            name = "پشتیبانی",
            logo = R.drawable.support24,
            route = Navigation.AboutUs.route
        ),
        DropDownMenuItems(
            name = "قوانین و مقررات",
            logo = R.drawable.rules,
            route = Navigation.AboutUs.route
        ),
        DropDownMenuItems(
            name = "درباره ما",
            logo = R.drawable.aboutus,
            route = Navigation.AboutUs.route
        ),
        DropDownMenuItems(
            name = "ارتباط با ما",
            logo = R.drawable.contactus,
            route = Navigation.AboutUs.route
        ),
        DropDownMenuItems(name = "خروج", logo = R.drawable.logout, isAction = true)
    )

    DropdownMenu(
        expanded = stateMenuExpand,
        onDismissRequest = {
            stateMenuExpand = false
        },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {

        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = OSLightOrang,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(R.drawable.userpicsmall),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .padding(horizontal = 8.dp)
                        )

                        // Enable navigation only when profile is not completed
                        Text(
                            text = profile.name.ifEmpty { "نام کاربری و شماره همراه خود را ثبت کنید" },
                            color = Color.Black,
                            fontSize = 14.sp,

                            modifier = Modifier.then (
                                if(profile.name.isEmpty()){
                                    Modifier.clickable{
                                    if (navController.currentDestination?.route != Navigation.EditProfile.route) {
                                        navController.navigate(Navigation.EditProfile.route)
                                    }
                                    stateMenuExpand = false
                                }}else
                                    Modifier
                            )
                        )
                    }

                    Text(
                        text = profile.phone,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }

                dropDownMenuItem.forEach { item ->

                    DropdownMenuItem(

                        text = {
                            Text(
                                text = item.name,
                                color = Color.Black,
                                style = TextStyle(
                                    textDirection = TextDirection.Rtl
                                )
                            )
                        },

                        leadingIcon = {
                            Image(
                                painter = painterResource(item.logo),
                                contentDescription = item.name
                            )
                        },

                        onClick = {

                            stateMenuExpand = false

                            if (item.isAction) {

                                profileViewModel.logout()

                                navController.navigate(Navigation.Login.route) {
                                    popUpTo(0)
                                }

                            } else {
                                item.route?.let {
                                    navController.navigate(it) {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}