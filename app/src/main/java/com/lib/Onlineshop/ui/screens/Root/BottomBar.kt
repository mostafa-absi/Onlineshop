package com.lib.Onlineshop.ui.screens.Root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lib.Onlineshop.R
import com.lib.Onlineshop.data.Model.BottomNavDataModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.theme.OSGrayText
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun BottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavDataModel(Navigation.Profile.route, "پروفایل", R.drawable.profile),
        BottomNavDataModel(Navigation.Cart.route, "سبد خرید", R.drawable.cart),
        BottomNavDataModel(Navigation.Category.route, "دسته بندی", R.drawable.category),
        BottomNavDataModel(Navigation.Home.route, "خانه", R.drawable.home)
    )
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {

        bottomNavItems.forEach { screen ->

            val isSelected = currentRoute == screen.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(Navigation.Home.route) {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .height(3.dp)
                                    .background(
                                        Color(0xFFE32A0D),
                                        shape = RoundedCornerShape(50)
                                    )
                            )
                            Spacer(Modifier.height(4.dp))
                        } else {
                            Spacer(Modifier.height(7.dp))
                        }

                        Image(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.title,
                        color = if (isSelected) Color(0xFFE32A0D) else OSGrayText
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                interactionSource = remember { NoRippleInteractionSource() } // برای نمایش ندادن اون قسمت خاکستری وقتی کلیک میکنی رو آیتم

            )
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions = MutableSharedFlow<Interaction>()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}