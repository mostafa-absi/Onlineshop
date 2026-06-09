package com.lib.Onlineshop.ui.screens.other

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp

@Composable
fun AboutUsScreen(navController: NavController) {

    val context = LocalContext.current

    BackgroundComp {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Spacer(Modifier.height(16.dp))
        TitleTextComp("ارتباط با ما", fontSize = 24 )

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(90.dp),
                    tint = Color(0xFFE32A0D)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Mostafa abbasi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Android Developer",
                    color = Color.Gray
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "\n این پروژه با معماری MVVM توسعه داده شده است.\n"+" لینک APIهای استفاده شده:",
                    textAlign = TextAlign.Center,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    color = Color.DarkGray
                )
                Text(
                    text = "https://fakestoreapi.com",
                    textAlign = TextAlign.Center,
                    style = TextStyle(textDirection = TextDirection.Ltr),
                    color = Color.DarkGray,
                    modifier = Modifier.clickable{
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            "https://fakestoreapi.com".toUri()
                        )
                        context.startActivity(intent)
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        AboutItem(
            title = "GitHub",
            icon = Icons.Default.Code,
            onClick = {

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "https://github.com/mostafa-absi".toUri()

                )

                context.startActivity(intent)
            }
        )

        Spacer(Modifier.height(10.dp))

        AboutItem(
            title = "Email",
            icon = Icons.Default.Email,
            onClick = {

                val intent = Intent(
                    Intent.ACTION_SENDTO,
                    "mailto:Mostafa.abbasi222@gmail.com".toUri()
                )

                context.startActivity(intent)
            }
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Version 1.0.0",
            color = Color.Gray
        )
    }
}}

@Composable
fun AboutItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFE32A0D)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}