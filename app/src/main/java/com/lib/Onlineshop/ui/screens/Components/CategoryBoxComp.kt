package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun CategoryBoxComp(
    category: String,
    navController: NavController,
    onClick: (String) -> Unit = {}
) {

    TextButton(
        onClick = {
            onClick(category)
        },
        modifier = Modifier
            .size(
                width = 105.dp,
                height = 128.dp
            )
            .background(
                Color.White,
                shape = RoundedCornerShape(21.dp)
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = categoryIcon(category),
                contentDescription = category,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                tint = Color.Unspecified
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = getCategoryTitle(category),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}




fun categoryIcon(category: String): ImageVector {

    return when (category) {

        "electronics" -> Icons.Default.Devices
        "jewelery" ->  Icons.Default.Diamond
        "men's clothing" ->  Icons.Default.Male
        "women's clothing" ->   Icons.Default.Female

        else ->   Icons.Default.Category
    }
}

fun getCategoryTitle(category: String): String {

    return when (category) {

        "electronics" -> "الکترونیک"

        "jewelery" -> "جواهرات"

        "men's clothing" -> "پوشاک مردانه"

        "women's clothing" -> "پوشاک زنانه"

        else -> category
    }
}

