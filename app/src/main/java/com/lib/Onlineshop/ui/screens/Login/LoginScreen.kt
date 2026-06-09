package com.lib.Onlineshop.ui.screens.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.LoginViewModel
import com.lib.Onlineshop.ViewModel.ProfileViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.theme.OSOrange

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    var usernameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()
    val netState by viewModel.internetLoginObserver.online.collectAsState(true)
    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state is LoginViewModel.LoginState.Success && netState) {
            navController.navigate(Navigation.Home.route) {
                popUpTo(Navigation.Login.route) {
                    inclusive = true
                }
            }
        }
        if (state is LoginViewModel.LoginState.Error){
            Toast.makeText(context,R.string.Login_Error, Toast.LENGTH_SHORT).show()
        }
    }

    SplashAndAuthBackground {
        OnlineShopTitle()
        Spacer(Modifier.height(4.dp))
        Text(
            stringResource(R.string.Login_Title),
            textAlign = TextAlign.Start,
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 6.dp)
        )

        LoginTextField(
            stateValue = usernameState,
            onValueChange = { usernameState = it },
            placeHolder = stringResource(R.string.Login_Username)
        )

        Spacer(Modifier.height(10.dp))

        LoginTextField(
            stateValue = passwordState,
            onValueChange = { passwordState = it },
            placeHolder = stringResource(R.string.Login_Password)
        )

        //check (username password and internet)
        Button(
            onClick = {
                if (!netState) {
                    Toast.makeText(
                        context,
                        R.string.Login_Internet_Check,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }
                viewModel.sendLoginData(usernameState, passwordState)
            },
            enabled = state !is LoginViewModel.LoginState.Loading,
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(horizontal = 27.dp, vertical = 10.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFD56552),
                            Color(0xFFE32A0D)
                        )
                    ), shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            if (state is LoginViewModel.LoginState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.DarkGray
                )
            } else {
                Text(
                    stringResource(R.string.Login_Login),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

        }

        // Login Hint
        if(state is LoginViewModel.LoginState.Error){
            Spacer(Modifier.height(20.dp))
            SelectionContainer{
                Text(
                    text = "username: donero   password: ewedon"
                    , color = Color.Black
                )
            }
        }
    }
}


@Composable
fun SplashAndAuthBackground(
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { content.invoke(this) }

    }
}

@Composable
fun OnlineShopTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.onlineshoplogo),
            "OnlineShopLogo",
            modifier = Modifier.size(width = 100.dp, height = 90.dp)
        )
        Spacer(Modifier.height(2.dp))
        Image(
            painterResource(R.drawable.onlineshoptitle),
            "OnlineShopTitle",
            modifier = Modifier.size(width = 130.dp, height = 40.dp)
        )
        Spacer(Modifier.height(2.dp))
        Row {
            Text("آنلاین شاپ", fontWeight = FontWeight.Bold, color = OSOrange)
            Spacer(Modifier.width(4.dp))
            Text("فروشگاه اینترنتی", fontWeight = FontWeight.Bold, color = Color.Black)

        }


    }
}

@Composable
private fun LoginTextField(
    stateValue: String,
    onValueChange: (String) -> Unit,
    placeHolder: String
) {
    OutlinedTextField(
        value = stateValue,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp),
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            Text(
                placeHolder,
                textAlign = TextAlign.Start,
                style = TextStyle(textDirection = TextDirection.Rtl),
                modifier = Modifier.fillMaxWidth()
            )
        },
        textStyle = TextStyle(Color.Black),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorContainerColor = Color.White,
            errorIndicatorColor = Color.Red,
            errorTrailingIconColor = Color.Red,

            )
    )
}
