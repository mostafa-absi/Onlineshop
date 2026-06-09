package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lib.Onlineshop.ViewModel.ProfileViewModel
import com.lib.Onlineshop.data.Model.UserProfileModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.screens.Components.ProfileBigHeaderComp
import com.lib.Onlineshop.ui.screens.Components.TextFieldComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp


@Composable
fun EditProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val profile by viewModel.profile.collectAsState()

    var usernameState by remember(profile.name) { mutableStateOf(profile.name) }
    var phoneState by remember(profile.phone) { mutableStateOf(profile.phone) }
    var emailState by remember(profile.email) { mutableStateOf(profile.email) }
    var birthState by remember(profile.birth) { mutableStateOf(profile.birth) }

    var isMale by remember(profile.gender) {
        mutableStateOf(profile.gender == "male")
    }
    var isFemale by remember(profile.gender) {
        mutableStateOf(profile.gender == "female")
    }





    BackgroundComp() {
        ProfileBigHeaderComp(navController, showUsernameAndPhoneNumber = false)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(10.dp))
            TitleTextComp("نام و نام خانوادگی:", true)
            TextFieldComp(
                state = usernameState,
                onValueChange = { usernameState = it },
                maxLength = 30,
                placeHolder = "علی علیزاده"
            )

            TitleTextComp("شماره همراه:", true)
            TextFieldComp(
                state = phoneState,
                onValueChange = { phoneState = it },
                keyboardValue = KeyboardType.Phone,
                maxLength = 11,
                placeHolder = "09121234567",
                direction = TextDirection.Ltr
            )

            TitleTextComp("ایمیل:")
            TextFieldComp(
                state = emailState,
                onValueChange = { emailState = it },
                keyboardValue = KeyboardType.Email,
                placeHolder = "bahram@gmail.com",
                direction = TextDirection.Ltr
            )

            TitleTextComp("تاریخ تولد:")
            TextFieldComp(
                state = birthState,
                onValueChange = { birthState = it },
                keyboardValue = KeyboardType.Unspecified,
                placeHolder = "مثال: 02-01-1370",
                maxLength = 10
            )
            TitleTextComp("جنسیت:")
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("مرد", color = Color.Black)
                RadioButton(
                    selected = isMale,
                    onClick = {
                        isMale = true
                        isFemale = false
                    }
                )
                Spacer(Modifier.width(10.dp))
                Text("زن", color = Color.Black)
                RadioButton(
                    selected = isFemale,
                    onClick = {
                        isFemale = true
                        isMale = false
                    }
                )
            }
            Spacer(Modifier.height(10.dp))

            GradientButton(
                onClick = {
                    viewModel.saveProfile(
                        UserProfileModel(
                            name = usernameState,
                            phone = phoneState,
                            email = emailState,
                            birth = birthState,
                            gender = if (isMale) "male" else "female"
                        )
                    )
                    Toast.makeText(context, "تغییرات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show()
                    navController.navigate(Navigation.Home.route) {
                        popUpTo(Navigation.EditProfile.route) { inclusive = true }
                    }
                },
                buttonName = "ثبت تغییرات"
            )
        }
    }
}



