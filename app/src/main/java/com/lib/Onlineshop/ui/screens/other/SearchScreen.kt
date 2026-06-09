package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.ProductSmallSizeComp
import com.lib.Onlineshop.ui.screens.Components.RowBoxComp
import com.lib.Onlineshop.ui.screens.Components.SearchBoxComp
import com.lib.Onlineshop.ui.screens.Root.ArrowBack



@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel()
) {

    val products by viewModel.products.collectAsState()
    var query by remember { mutableStateOf("") }

    BackgroundComp {


        //-------------------Search Space

        Spacer(Modifier.height(10.dp))
        RowBoxComp (horizontalArrangement = Arrangement.SpaceBetween){
            ArrowBack(navController)

            Text(
                text = "جستجو", fontSize = 22.sp,
                color = Color.Black, fontWeight = FontWeight.Bold,
                style = TextStyle(textDirection = TextDirection.Rtl),
            )

        }
        Spacer(Modifier.height(10.dp))
        RowBoxComp {

            SearchBoxComp(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.search(it)
                }
            )
        }
        Spacer(Modifier.height(10.dp))

        //-------------------Products Space
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            items(products) { product ->

                ProductSmallSizeComp(
                    product = product,
                    onClick = {
                        navController.navigate(
                            Navigation.Product.route + "/${product.id}"
                        )
                    },
                    onAddToCart = {
                        viewModel.addToCart(product.id)
                    }
                )
            }
        }
    }}


