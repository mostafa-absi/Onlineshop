package com.lib.Onlineshop.ui.screens.Home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.CartItemCard
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val cartProducts by viewModel.cartProducts.collectAsState()
    val context = LocalContext.current

    BackgroundComp() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(10.dp))
            TitleTextComp("سبد خرید", fontSize = 22)
            Spacer(Modifier.height(10.dp))

            if (cartProducts.isEmpty()) {
                Spacer(Modifier.height(50.dp))
                Text(
                    text = "سبد خرید شما خالی است",
                    color = Color.Black
                )

            } else {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            horizontal = 15.dp
                        )
                    ) {

                        items(
                            cartProducts,
                            key = { it.id }
                        ) { product ->

                            CartItemCard(
                                product = product,
                                onIncrease = {
                                    viewModel.addToCart(product.id)
                                },
                                onDecrease = {
                                    viewModel.removeFromCart(product.id)
                                }
                            )
                        }
                    }

                    GradientButton({
                        viewModel.clearCart()

                        Toast.makeText(
                            context,
                            "خرید با موفقیت ثبت شد",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, "تکمیل خرید")

                }
            }
        }
    }
}
