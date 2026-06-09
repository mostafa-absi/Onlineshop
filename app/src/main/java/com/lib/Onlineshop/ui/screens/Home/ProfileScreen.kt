package com.lib.Onlineshop.ui.screens.Home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.ProfileBigHeaderComp

@Composable
fun ProfileScreen(navController: NavHostController) {

    val context = LocalContext.current
    BackgroundComp() {
        ProfileBigHeaderComp(navController)
        Spacer(Modifier.height(5.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            item { UserProfileItem(image = R.drawable.changepassworduserprofile , onClick = {navController.navigateFromProfile(Navigation.ChangePassword.route)}) }
            item { UserProfileItem(image = R.drawable.mynotificationsuserprofile, onClick = {navController.navigateFromProfile(Navigation.Notification.route)}) }
            item { UserProfileItem(image = R.drawable.myfavoritesuserprofile, onClick = {navController.navigateFromProfile(Navigation.Favorite.route)}) }
            item { UserProfileItem(image = R.drawable.myaddressesuserprofile, onClick = {navController.navigateFromProfile(Navigation.MyAddress.route)} ) }
            item { UserProfileItem(image = R.drawable.myordersuserprofile, onClick = {Toast.makeText(context, "در حال پیاده‌سازی", Toast.LENGTH_SHORT).show()}) }
            item { UserProfileItem(image = R.drawable.myshoppingexperienceuserprofle, onClick = {Toast.makeText(context, "در حال پیاده‌سازی", Toast.LENGTH_SHORT).show()}) }
        }
    }
}


@Composable
private fun UserProfileItem(
     image: Int,
     onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(image),
        "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(1f).padding(10.dp)
            .clickable{onClick()}
    )

}

// Extension function
fun NavController.navigateFromProfile(route: String) {
    navigate(route) {
        popUpTo(Navigation.Profile.route)
    }
}

