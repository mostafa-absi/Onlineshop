package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.LoginViewModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.screens.Components.ProfileSmallHeaderComp
import com.lib.Onlineshop.ui.screens.Components.TextFieldComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp
import androidx.compose.runtime.collectAsState


@Composable
fun ChangePasswordScreen(navController: NavController,
                         viewModel: LoginViewModel= hiltViewModel()
){
    //just for test - shouldn't save password in Application
    val pass = viewModel.password.collectAsState().value

    val context = LocalContext.current

    var oldPassState by remember { mutableStateOf("") }
    var newPassState by remember { mutableStateOf("") }
    var repeatNewPassState by remember { mutableStateOf("") }

    BackgroundComp{
        ProfileSmallHeaderComp()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleTextComp("تغییر رمز", fontSize = 18)
            Spacer(Modifier.height(5.dp))

            Text("""
        در انتخاب رمز عبور موارد زیر را در نظر بگیرید:
        
        • رمز عبور باید حداقل ۸ کاراکتر باشد
        • شامل حروف و عدد باشد
        • شامل علامت باشد (!@#$%)
        • از حروف بزرگ و کوچک استفاده شود
    """.trimIndent(),
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    fontSize = 15.sp,
                    color = Color.Black
                ))
            Spacer(Modifier.height(10.dp))

            TitleTextComp("رمز عبور فعلی:", isNecessary = true, fontSize = 16)
            Spacer(Modifier.height(2.dp))
            TextFieldComp(
                state = oldPassState,
                onValueChange = { oldPassState = it },
                placeHolder = "********",
            )

            TitleTextComp("رمز عبور جدید:", isNecessary = true, fontSize = 16)
            TextFieldComp(
                state = newPassState,
                onValueChange = { newPassState = it },
                keyboardValue = KeyboardType.Password,
                placeHolder = "********",
            )

            TitleTextComp("تکرار رمز عبور جدید:", isNecessary = true, fontSize = 16)
            TextFieldComp(
                state = repeatNewPassState,
                onValueChange = { repeatNewPassState = it },
                keyboardValue = KeyboardType.Password,
                placeHolder = "********",
            )
            Spacer(Modifier.height(10.dp))

            GradientButton(
                {
                    if(newPassState == repeatNewPassState){
                        if (oldPassState == pass.toString()){
                            Toast.makeText(context, "عملیات موفق بود\n(ولی پسورد تغییر نکرد چون پروژه تستیه)", Toast.LENGTH_SHORT).show()
                           /* navController.navigate(Navigation.Home.route){
                                popUpTo(Navigation.ChangePassword.route) { inclusive =true}
                            }*/
                            navController.popBackStack()

                        }else{
                            Toast.makeText(context, "رمز عبور قبلی اشتباه است", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(context, "رمز عبور جدید یکسان نیست", Toast.LENGTH_SHORT).show()
                    }
                },
                buttonName = "تغییر رمز عبور"
            )
        }
    }
}