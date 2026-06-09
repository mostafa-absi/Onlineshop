package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.AddressViewModel
import com.lib.Onlineshop.data.Model.AddressModel
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.screens.Components.ProfileSmallHeaderComp
import com.lib.Onlineshop.ui.screens.Components.TextFieldComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp

@Composable
fun AddAddressScreen(
    navController: NavController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var receiverNameState by remember { mutableStateOf("") }
    var addressState by remember { mutableStateOf("") }
    var postalCodeState by remember { mutableStateOf("") }
    var phoneNumberState by remember { mutableStateOf("") }

    BackgroundComp {
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
            Spacer(Modifier.height(15.dp))
            TitleTextComp("آدرس", fontSize = 22)
            TitleTextComp("لطفا تمامی قسمت‌ها را تکمیل کنید", fontSize = 16)
            Spacer(Modifier.height(15.dp))

            TitleTextComp("نام گیرنده:", fontSize = 18, isNecessary = true)
            TextFieldComp(
                state = receiverNameState,
                onValueChange = { receiverNameState = it },
                placeHolder = "محمد عزیزی",
                maxLength = 50
            )

            TitleTextComp("آدرس:", fontSize = 18, isNecessary = true)
            TextFieldComp(
                state = addressState,
                onValueChange = { addressState = it },
                placeHolder = "بیرجند - کوچه غفاری - پلاک ۹",
                maxLength = 200
            )

            TitleTextComp("کد پستی:", fontSize = 18, isNecessary = true)
            TextFieldComp(
                state = postalCodeState,
                onValueChange = { postalCodeState = it },
                placeHolder = "0123456789",
                maxLength = 10,
                keyboardValue = KeyboardType.Number
            )

            TitleTextComp("شماره همراه گیرنده:", fontSize = 18, isNecessary = true)
            TextFieldComp(
                state = phoneNumberState,
                onValueChange = { phoneNumberState = it },
                placeHolder = "09121234567",
                maxLength = 11,
                keyboardValue = KeyboardType.Number
            )
            Spacer(Modifier.height(10.dp))

            GradientButton({
                if (
                    receiverNameState.isBlank() ||
                    phoneNumberState.isBlank() ||
                    postalCodeState.isBlank() ||
                    addressState.isBlank()
                ) {
                    Toast.makeText(context, "لطفا تمامی فیلدها را تکمیل کنید",Toast.LENGTH_SHORT).show()
                    return@GradientButton
                }
                viewModel.addAddress(
                    AddressModel(
                        receiverName = receiverNameState,
                        phoneNumber = phoneNumberState,
                        address = addressState,
                        postalCode = postalCodeState
                    )
                )
                Toast.makeText(context,"آدرس با موفقیت ثبت شد",Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            },
                buttonName = "ثبت آدرس")
        }
    }
}