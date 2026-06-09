package com.lib.Onlineshop.navigation

sealed class Navigation(val route: String) {
    object Splash : Navigation("splash")
    object Login : Navigation("login")

    object Home : Navigation("home")
    object Category : Navigation("category")
    object Cart : Navigation("cart")
    object Profile : Navigation("profile")


    object Notification : Navigation("notification")
    object Search : Navigation("search")
    object EditProfile : Navigation("editProfile")
    object ChangePassword : Navigation("changePassword")
    object MyAddress : Navigation("myAddress")
    object AddAddress : Navigation("AddAddress")
    object SubCategory : Navigation("subCategory")
    object Product : Navigation("product")
    object Favorite : Navigation("favorite")
    object AboutUs : Navigation("aboutUs")

    companion object {
        val mainRoute = listOf(
            Home.route,
            Category.route,
            Cart.route,
            Profile.route
        )
        val hideRoute = listOf(
            Splash.route,
            Login.route,
            Search.route
        )
    }

}