package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.lib.Onlineshop.ui.screens.Components.ProductCard
import com.lib.Onlineshop.ui.screens.Components.RowBoxComp
import com.lib.Onlineshop.ui.screens.Components.SearchBoxComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp
import com.lib.Onlineshop.ui.screens.Components.getCategoryTitle

@Composable
fun SubCategoryScreen(
    navController: NavController,
    categoryName: String,
    viewModel: ProductViewModel = hiltViewModel()
) {
    var context = LocalContext.current
    val products by viewModel.products.collectAsState()
    val filtered = products.filter { it.category == categoryName }

    BackgroundComp {

        //-----------------------Title Space
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(15.dp))
            TitleTextComp(getCategoryTitle(categoryName), fontSize = 20)
            Spacer(Modifier.height(15.dp))

            //-----------------------Search Space
            RowBoxComp(horizontalPadding = 0) {
                SearchBoxComp(
                    enabled = false,
                    onClick = {
                        navController.navigate(Navigation.Search.route)
                    })
            }
            Spacer(Modifier.height(10.dp))

            //-------------------------- Most Sale space
            LazyRow(reverseLayout = true) {
                items(filtered) { product ->
                    ProductCard(
                        product = product,
                        onClick = { id ->
                            navController.navigate(Navigation.Product.route + "/${id}")
                        },
                        onAddToCart = { id ->
                            viewModel.addToCart(id)
                        }
                    )
                }
            }
        }
    }
}