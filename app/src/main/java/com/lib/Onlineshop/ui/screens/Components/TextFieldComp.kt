package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldComp(
    state: String,
    onValueChange: (String) -> Unit,
    placeHolder: String = "",
    keyboardValue: KeyboardType = KeyboardType.Text,
    maxLength: Int = 250,
    direction: TextDirection = TextDirection.Rtl,
    leadingIcon: (@Composable (() -> Unit))? = null
) {
    TextField(
        value = state,
        onValueChange = { input ->
            if (input.length <= maxLength) {
                onValueChange(input)
            }
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, top = 3.dp),
        textStyle = TextStyle(
            textDirection = direction,
            fontSize = 14.sp,
            color = Color.Black
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFFE02508)
        ),
        placeholder = {
            Text(
                placeHolder,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.6f),
                fontSize = 12.sp,
                style = TextStyle(
                    textDirection = direction,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardValue),
        singleLine = true,
        leadingIcon = leadingIcon
    )
}