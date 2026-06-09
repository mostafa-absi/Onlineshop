package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleTextComp(titleString: String, isNecessary: Boolean = false, fontSize: Int = 16) {
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
        if (isNecessary) {
            Text(
                "*", style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    fontSize = 16.sp,
                    color = Color.Red
                )
            )
        }
        Text(
            titleString,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                textDirection = TextDirection.Rtl,
                fontSize = 15.sp,
                color = Color.Black
            )
        )
        Spacer(Modifier.width(2.dp))

    }
}