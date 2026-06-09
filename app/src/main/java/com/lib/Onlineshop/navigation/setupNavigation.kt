package com.lib.Onlineshop.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lib.Onlineshop.ui.screens.Home.CartScreen
import com.lib.Onlineshop.ui.screens.Home.CategoryScreen
import com.lib.Onlineshop.ui.screens.Home.HomeScreen
import com.lib.Onlineshop.ui.screens.Home.ProfileScreen
import com.lib.Onlineshop.ui.screens.Login.LoginScreen
import com.lib.Onlineshop.ui.screens.Login.SplashView
import com.lib.Onlineshop.ui.screens.other.AboutUsScreen
import com.lib.Onlineshop.ui.screens.other.AddAddressScreen
import com.lib.Onlineshop.ui.screens.other.ChangePasswordScreen
import com.lib.Onlineshop.ui.screens.other.EditProfileScreen
import com.lib.Onlineshop.ui.screens.other.FavoriteScreen
import com.lib.Onlineshop.ui.screens.other.MyAddressScreen
import com.lib.Onlineshop.ui.screens.other.NotificationScreen
import com.lib.Onlineshop.ui.screens.other.ProductScreen
import com.lib.Onlineshop.ui.screens.other.SearchScreen
import com.lib.Onlineshop.ui.screens.other.SubCategoryScreen


@Composable
fun SetupNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.Splash.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        //--------------Login Route
        composable(Navigation.Splash.route) { SplashView(navController) }
        composable(Navigation.Login.route) { LoginScreen(navController) }

        //--------------Home Route
        composable(Navigation.Home.route) { HomeScreen(navController) }
        composable(Navigation.Category.route) { CategoryScreen(navController) }
        composable(Navigation.Cart.route) { CartScreen(navController) }
        composable(Navigation.Profile.route) { ProfileScreen(navController) }

        //--------------Other Route
        composable(Navigation.Notification.route) { NotificationScreen(navController) }
        composable(Navigation.Search.route) { SearchScreen(navController) }
        composable(Navigation.EditProfile.route) { EditProfileScreen(navController) }
        composable(Navigation.ChangePassword.route) { ChangePasswordScreen(navController) }
        composable(Navigation.MyAddress.route) { MyAddressScreen(navController) }
        composable(Navigation.AddAddress.route) { AddAddressScreen(navController) }
        composable(Navigation.Favorite.route) { FavoriteScreen(navController) }
        composable(Navigation.AboutUs.route) { AboutUsScreen(navController) }


        composable( route = Navigation.SubCategory.route + "/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType }
            )){   val categoryName =
                it.arguments?.getString("categoryName")?: ""
            SubCategoryScreen(
                navController = navController,
                categoryName = categoryName
            )
        }

        composable( route = Navigation.Product.route + "/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType }
            )){   val productId =
            it.arguments?.getInt("productId")?: 0
            ProductScreen(
                navController = navController,
                productId = productId
            )
        }

    }
}