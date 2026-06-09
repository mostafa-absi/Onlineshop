package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.ProfileViewModel

@Composable
fun ProfileSmallHeaderComp(
    viewModel: ProfileViewModel= hiltViewModel()
) {
    val profile by viewModel.profile.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE32A0D),
                        Color(0xFFFD937F)
                    )
                ), shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    profile.name.ifEmpty { "لطفا برای تکمیل پروفایل خود اقدام کنید"
                                         },
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(profile.phone, color = Color.White)
            }
            Image(painter = painterResource(R.drawable.userpicbigwithborder), "")
        }
    }
}