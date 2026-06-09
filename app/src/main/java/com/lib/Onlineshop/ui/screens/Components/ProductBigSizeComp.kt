package com.lib.Onlineshop.ui.screens.Components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.ui.theme.OSCardBackground


@Composable
fun ProductCard(
    product: ProductModel,
    onClick: (Int) -> Unit,
    onAddToCart: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(OSCardBackground, RoundedCornerShape(30.dp))
            .size(width = 253.dp, height = 330.dp)
            .clickable {
                onClick(product.id)
            }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .padding(10.dp)
                    .size(180.dp)
            )
            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .height(130.dp)
                    .background(Color.White, RoundedCornerShape(20.dp))
            ) {

                IconButton(
                    onClick = {
                        onAddToCart(product.id)
                        Toast.makeText(context, "محصول با موفقیت اضافه شد", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (215).dp)
                        .background(Color(0xFFE32A0D), CircleShape)
                        .size(35.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(17.dp)
                    )
                }

                Text(
                    product.title,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart),
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        " Price: $${product.price}", color = Color.Black,
                    )
                }
            }
        }
    }
}