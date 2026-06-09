package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.ProductSmallSizeComp
import com.lib.Onlineshop.ui.screens.Components.ProfileSmallHeaderComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val favorites by viewModel.favorites.collectAsState()

    BackgroundComp {
        ProfileSmallHeaderComp()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Spacer(Modifier.height(20.dp))

        TitleTextComp("علاقه‌مندی‌های من", fontSize = 22)
        Spacer(Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            items(favorites) { product ->

                ProductSmallSizeComp(
                    product = product,
                    onClick = {
                        navController.navigate(
                            Navigation.Product.route + "/${product.id}"
                        )
                    },
                    onAddToCart = {
                        viewModel.addToCart(product.id)
                    },
                    showFavoriteToggle = true,
                    onFavoriteClick = {viewModel.toggleFavorite(product.id)
                        Toast.makeText(context, "محصول از علاقه‌مندی‌ها حذف شد", Toast.LENGTH_SHORT).show()}
                )
            }


        }
    }
}}