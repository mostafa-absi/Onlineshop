package com.lib.Onlineshop.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.CategoryBoxComp
import com.lib.Onlineshop.ui.screens.Components.ProductCard
import com.lib.Onlineshop.ui.screens.Components.RowBoxComp
import com.lib.Onlineshop.ui.screens.Components.SearchBoxComp
import com.lib.Onlineshop.ui.screens.Components.SliderStatic
import com.lib.Onlineshop.ui.theme.OSOrange
import com.lib.Onlineshop.ui.theme.OSindicator
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val product by viewModel.products.collectAsState()
    val categories = remember(product) {
        product
            .map { it.category }
            .distinct()
    }
    val products = remember(product) {
        product.map { it.id }
    }

    BackgroundComp {
        //------------------------ Slider Space

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(5.dp))
            SliderStatic()
            Spacer(Modifier.height(5.dp))
            RowBoxComp {
                Text(
                    text = "تنها با یک کلیک خرید کن!", fontSize = 22.sp,
                    color = Color.Black, fontWeight = FontWeight.Bold,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                )
            }
            Spacer(Modifier.height(10.dp))

            //-----------------------Search Space
            RowBoxComp {

                SearchBoxComp(
                    enabled = false,
                    onClick = {
                        navController.navigate(Navigation.Search.route)
                    })
            }
            Spacer(Modifier.height(10.dp))

            //-----------------------Category Space
            LazyRow(
                reverseLayout = true,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                items(categories) { categories ->
                    CategoryBoxComp(
                        category = categories, navController = navController,
                        onClick = { selectedCategory ->
                            navController.navigate(Navigation.SubCategory.route + "/${selectedCategory}")
                        })
                }
            }

            //-------------------------- Best Sell space

            BestSelling(navController)
            LazyRow(reverseLayout = true,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                items(product) { item ->

                    ProductCard(
                        product = item,
                        onClick = { id ->
                            navController.navigate(Navigation.Product.route + "/$id")
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

@Composable
fun BestSelling(navController: NavController?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton({/*navController?.navigate(OSNavigation.allProducts.route) */}) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    "",
                    tint = OSOrange
                )
            }
            Text("مشاهده همه", color = Color.Black)
        }
        Text(" پرفروش ترین‌ها", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}


