package com.lib.Onlineshop.ui.screens.Components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lib.Onlineshop.R
import com.lib.Onlineshop.data.Model.ProductModel
import com.lib.Onlineshop.ui.theme.OSCardBackground

@Composable
fun ProductSmallSizeComp(
    product: ProductModel,
    showFavoriteToggle: Boolean=false,
    onFavoriteClick: (() -> Unit)? = null,
    onClick: (Int) -> Unit,
    onAddToCart: (Int) -> Unit = {}
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(6.dp)
            .background(
                color = OSCardBackground,
                shape = RoundedCornerShape(24.dp)
            )
            .size(width = 150.dp, height = 220.dp)
            .clickable {
                onClick(product.id)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(110.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .height(85.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    )
            ) {

                IconButton(
                    onClick = {
                        onAddToCart(product.id)

                        Toast.makeText(
                            context,
                            "محصول اضافه شد",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = 12.dp)
                        .background(
                            Color(0xFFE32A0D),
                            CircleShape
                        )
                        .size(26.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 8.dp,
                            end = 30.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                ) {

                    Text(
                        text = product.title,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "$${product.price}",
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                }
            }
        }
        //----------------- being in Favorite Screen or not

        if(showFavoriteToggle){
            Image(
                painter = painterResource(
                    if (product.isFavorite)
                        R.drawable.bookmarkf
                    else
                        R.drawable.bookmarkm
                ),
                "",
                modifier = Modifier.align (Alignment.TopStart).padding(top = 10.dp, start = 10.dp)
                    .clickable { onFavoriteClick?.invoke() }
            )
        }
    }
}