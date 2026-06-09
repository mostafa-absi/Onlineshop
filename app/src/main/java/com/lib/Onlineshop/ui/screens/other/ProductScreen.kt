package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.ProductViewModel
import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.theme.OSCardBackground
import java.text.NumberFormat

@Composable
fun ProductScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val products by viewModel.products.collectAsState()
    val product = products.find { it.id == productId }
    BackgroundComp {
        if (product == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@BackgroundComp
        }
        val formattedPrice = NumberFormat.getNumberInstance().format(product.price)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState())

        ) {
            //---------------------- Image Space
            ProductImage(product)

            //---------------------- Icons Space
            IconBar(product = product,
                onFavoriteClick = {
                    val message =
                        if (product.isFavorite)
                            "محصول از علاقه‌مندی‌ها حذف شد"
                        else
                            "محصول به علاقه‌مندی‌ها اضافه شد"

                    viewModel.toggleFavorite(product.id)

                    Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                })

            //---------------------- Description and Buy Button Space

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White, shape = RoundedCornerShape(topEnd = 25.dp))
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)

            ) {

                Spacer(Modifier.height(20.dp))

                ProductInfo(
                    product = product,formattedPrice = formattedPrice)

                Spacer(Modifier.height(20.dp))

                ProductDescription(product)

                Spacer(Modifier.height(30.dp))

                GradientButton(
                    onClick = {
                        viewModel.addToCart(product.id)
                        Toast.makeText(context, "محصول با موفقیت اضافه شد", Toast.LENGTH_SHORT)
                            .show()
                    },
                    buttonName = "افزودن به سبد خرید"
                )
            }
        }
    }
}


@Composable
private fun ProductImage(
    product: ProductModel
) {

    Card(
        shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp, bottomEnd = 25.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = OSCardBackground),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ProductInfo(
    product: ProductModel,
    formattedPrice: String
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = product.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = product.category,
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "$formattedPrice $",
            color = Color(0xFFE32A0D),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProductDescription(
    product: ProductModel
) {

    Text(
        text = "توضیحات",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            textDirection = TextDirection.Rtl
        )
    )

    Spacer(Modifier.height(10.dp))

    Text(
        text = product.description,
        style = TextStyle(
            textDirection = TextDirection.Ltr
        ),
        textAlign = TextAlign.Start,
        color = Color.DarkGray,
        fontSize = 16.sp
    )
}


@Composable
private fun IconBar(
    product: ProductModel,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.5f)
            .height(40.dp)
            .background(
                Color.White,
                RoundedCornerShape(topEnd = 40.dp)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.messagesmahsol),
                "",
                modifier = Modifier.weight(0.2f)
            )
            Image(
                painter = painterResource(
                    if (product.isFavorite)
                        R.drawable.bookmarkf
                    else
                        R.drawable.bookmarkm
                ),
                "",
                modifier = Modifier
                    .weight(0.2f)
                    .clickable {
                        onFavoriteClick()
                    }
            )
            Image(
                painter = painterResource(R.drawable.rating),
                "",
                modifier = Modifier.weight(0.2f)
            )
            Image(
                painter = painterResource(R.drawable.share),
                "",
                modifier = Modifier.weight(0.2f)
            )
        }
    }
}
