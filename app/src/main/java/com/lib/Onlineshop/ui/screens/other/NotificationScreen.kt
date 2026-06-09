package com.lib.Onlineshop.ui.screens.other

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ui.screens.Components.ProfileSmallHeaderComp
import com.lib.Onlineshop.ui.theme.OSCardNotifications
import com.lib.Onlineshop.ui.theme.OSCheckBoxInside
import com.lib.Onlineshop.ui.theme.OSGrayText

@Composable
fun NotificationScreen(navController: NavController){

    val stateCheckBoxNotification = remember { mutableStateOf(false) }
    val stateCheckBoxNotification2 = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        //-------------- ما دوتا column تو در تو ساختیم که محتوای Scroll پذیر بره زیر SmallHeader ------------
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileSmallHeaderComp()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.End
            ) {
                item {
                    Text(
                        "اعلانات من",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Right,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CheckBoxNotifications(stateCheckBoxNotification)
                        Text("خوانده نشده", color = Color.Black)
                        CheckBoxNotifications(stateCheckBoxNotification2)
                        Text("خوانده شده", color = Color.Black)
                    }
                }
                items(100) {NotificationMessage()}

            }
        }
    }
}


//--------------- فانکشن چک باکس -------------
@Composable
fun CheckBoxNotifications(stateCheckBoxNotification: MutableState<Boolean>) {
    Checkbox(
        checked = stateCheckBoxNotification.value,
        onCheckedChange = { stateCheckBoxNotification.value = it },
        colors = CheckboxDefaults.colors(
            checkedColor = OSCheckBoxInside,
            disabledCheckedColor = Color.Black,
            checkmarkColor = Color.Transparent,
            disabledIndeterminateColor = Color.Black,
            disabledUncheckedColor = Color.Black
        )
    )
}


// ---------------- این فانکشن برای باکس پیام هایی که نمایش داده میشه هست --------------
@Composable
fun NotificationMessage() {
    var stateExpandCardNotifications by remember { mutableStateOf(false) }
    val stateRotateCardNotification by animateFloatAsState(targetValue = if (stateExpandCardNotifications) 180f else 0f)
    Spacer(Modifier.height(10.dp))
    Column(modifier = Modifier.background(Color.White, shape = RoundedCornerShape(18.dp))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("۲۶ مهر ۱۴۰۲", color = OSGrayText, style = TextStyle(textDirection = TextDirection.Rtl))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text("بازگشت وجه", color = Color.Black)
                    Text("غرفه دار", color = OSGrayText)
                }
                Image(painter = painterResource(R.drawable.pocketnotif), "", modifier = Modifier.size(45.dp))


            }

        }
        Card(
            modifier = Modifier
                .fillMaxWidth().padding(start = 10.dp, end = 10.dp , bottom = 15.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 600)),
            onClick = { stateExpandCardNotifications = !stateExpandCardNotifications }
        ) {
            Column (modifier = Modifier.background(color = OSCardNotifications).padding(10.dp)){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow),
                        "",
                        modifier = Modifier.rotate(stateRotateCardNotification)
                    )
                    Text("مشاهده اعلان", modifier = Modifier.padding(horizontal = 4.dp), color = Color.Black)

                }
                if ( stateExpandCardNotifications){

                    HyperlinkTextFarsi(
                        fullText = "متن اعلان در اینجا نوشته می‌شود. ممکن است این اعلان لینک داشته باشد. متن اعلان در اینجا نوشته می‌شود. ممکن است این اعلان لینک داشته باشد.",
                        linkText = "لینک",
                        hyperlinks = "https://alirezaahmadi.info",
                        fontSize = 16.sp
                    )

                }

            }
        }


    }
}



// ------------  این فانکشن برای لینک دار کردن متن‌هایی که توی پیام میاد هست --------------
@Composable
fun HyperlinkTextFarsi(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: String,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Normal,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    hyperlinks: String = "https://google.com", // اینجا google میزاریم که اگه لینک ندادیم Crash نکنه
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        val startIndex = fullText.indexOf(linkText)
        if (startIndex >= 0) {
            val endIndex = startIndex + linkText.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    Text(
        text = annotatedString,color= Color.Black, fontWeight = FontWeight.Bold,
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {    // بررسی کلیک روی لینک‌ها
            annotatedString.getStringAnnotations(tag = "URL", start = 0, end = annotatedString.length)
                .firstOrNull()?.let { uriHandler.openUri(it.item) }
        },
        style = TextStyle(textDirection = TextDirection.Rtl) // RTL برای متن فارسی
    )
}
