package com.lib.Onlineshop.ui.screens.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.lib.Onlineshop.navigation.Navigation
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.CategoryBoxComp
import com.lib.Onlineshop.ui.screens.Components.RowBoxComp
import com.lib.Onlineshop.ui.screens.Components.SliderStatic

@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val product by viewModel.products.collectAsState()
    val categories = remember(product) {
        product
            .map { it.category }
            .distinct()
    }

    BackgroundComp {

        //------------------------ Slider Space
        Spacer(Modifier.height(5.dp))
        SliderStatic()
        Spacer(Modifier.height(5.dp))
        RowBoxComp {
            Text(
                text = "دسته‌ بندی", fontSize = 22.sp,
                color = Color.Black, fontWeight = FontWeight.Bold,
                style = TextStyle(textDirection = TextDirection.Rtl),
            )
        }
        Spacer(Modifier.height(10.dp))

        //------------------------ Category Space
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            itemVerticalAlignment = Alignment.CenterVertically
        ) {

            categories.forEach { category ->
                CategoryBoxComp(
                    category = category, navController = navController,
                    onClick = { selectedCategory ->
                        navController.navigate(Navigation.SubCategory.route + "/${selectedCategory}")
                    }
                )
            }
        }
    }
}