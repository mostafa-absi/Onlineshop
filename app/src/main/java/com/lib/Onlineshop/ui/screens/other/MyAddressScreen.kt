package com.lib.Onlineshop.ui.screens.other

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.lib.Onlineshop.ViewModel.AddressViewModel
import com.lib.Onlineshop.data.Model.AddressModel
import com.lib.Onlineshop.navigation.Navigation
import com.lib.Onlineshop.ui.screens.Components.BackgroundComp
import com.lib.Onlineshop.ui.screens.Components.GradientButton
import com.lib.Onlineshop.ui.screens.Components.ProfileSmallHeaderComp
import com.lib.Onlineshop.ui.screens.Components.TitleTextComp

@Composable
fun MyAddressScreen(
    navController: NavController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    val address by viewModel.addresses.collectAsState()
    val context = LocalContext.current
    BackgroundComp {
        ProfileSmallHeaderComp()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(5.dp))
            TitleTextComp("آدرس‌های من", fontSize = 22)
            Spacer(Modifier.height(15.dp))

            if (address.isEmpty()) {
                Text(
                    "هنوز آدرسی ثبت نشده است",
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            } else {
                address.forEach {
                    AddressCard(
                        address = it,
                        onSelect = {
                            viewModel.setDefaultAddress(it.id)
                        },
                        onEdit = {
                            Toast.makeText(
                                context,
                                "این قابلیت غیر فعال است \nلطفا حذف و سپس اضافه کنید",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onDelete = {
                            viewModel.deleteAddress(it.id)
                            Toast.makeText(
                                context,
                                "آدرس با موفقیت حذف شد",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            GradientButton({
                navController.navigate(Navigation.AddAddress.route) {
                    popUpTo(Navigation.MyAddress.route) { inclusive = true }
                }
            }, buttonName = "ثبت آدرس جدید")
            Spacer(Modifier.height(10.dp))
        }
    }
}


@Composable
fun AddressCard(
    address: AddressModel,
    onSelect: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(
            1.dp,
            if (address.isDefault)
                Color(0xFFE32A0D)
            else
                Color.Transparent
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            InfoRow(
                title = "گیرنده",
                value = address.receiverName
            )

            Spacer(Modifier.height(10.dp))

            InfoRow(
                title = "آدرس",
                value = address.address
            )

            Spacer(Modifier.height(10.dp))

            InfoRow(
                title = "کد پستی",
                value = address.postalCode
            )

            Spacer(Modifier.height(10.dp))

            InfoRow(
                title = "شماره همراه",
                value = address.phoneNumber
            )

            Spacer(Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = address.isDefault,
                        onClick = onSelect
                    )

                    Text(
                        "آدرس پیش فرض", color = Color.Black
                    )
                }

                SmallActionButton(
                    text = "ویرایش",
                    onClick = onEdit
                )

                Spacer(Modifier.width(8.dp))

                SmallActionButton(
                    text = "حذف",
                    onClick = onDelete
                )
            }
        }
    }
}


@Composable
private fun InfoRow(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        Text(
            text = value,
            color = Color.Gray,
            style = TextStyle(
                textDirection = TextDirection.Rtl
            )
        )
        Text(
            text = "$title: ", color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                textDirection = TextDirection.Rtl
            )
        )

    }
}

@Composable
fun SmallActionButton(
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xFFF3EEE9)
        )
    ) {

        Text(
            text = text,
            color = if (text == "ویرایش")
                Color(0xFFE32A0D)
            else
                Color.DarkGray,
            fontWeight = FontWeight.Bold
        )
    }
}
