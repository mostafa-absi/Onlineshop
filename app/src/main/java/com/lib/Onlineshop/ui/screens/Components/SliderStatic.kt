package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ui.theme.OSindicator
import kotlinx.coroutines.delay

@Composable
fun SliderStatic() {

    val imageList = listOf(
        R.drawable.ibanner,
        R.drawable.ibanner2,
        R.drawable.ibanner3,
        R.drawable.ibanner4,
        R.drawable.ibanner5
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageList.size }
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)

            val nextPage =
                (pagerState.currentPage + 1) % imageList.size

            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(200.dp)
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {

            HorizontalPager(
                state = pagerState,
                pageSpacing = 5.dp
            ) { page ->

                Image(
                    painter = painterResource(imageList[page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            SliderPageIndicator(
                currentPage = pagerState.currentPage,
                count = imageList.size
            )
        }
    }
}

@Composable
private fun SliderPageIndicator(
    currentPage: Int,
    count: Int
) {

    val rtlPage = count - 1 - currentPage

    Row(
        modifier = Modifier
            .background(
                color = OSindicator,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) { index ->

            val isSelected = index == rtlPage

            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(
                        width = if (isSelected) 14.dp else 6.dp,
                        height = 6.dp
                    )
                    .background(
                        color = if (isSelected) Color.White else Color.LightGray,
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}