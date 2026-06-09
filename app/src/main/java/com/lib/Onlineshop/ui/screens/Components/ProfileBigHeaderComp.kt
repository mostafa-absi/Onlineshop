package com.lib.Onlineshop.ui.screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lib.Onlineshop.R
import com.lib.Onlineshop.ViewModel.ProfileViewModel
import com.lib.Onlineshop.navigation.Navigation

@Composable
fun ProfileBigHeaderComp(navController: NavHostController,
                         showUsernameAndPhoneNumber: Boolean=true,
                         profileViewModel: ProfileViewModel= hiltViewModel()
) {
    val profile by profileViewModel.profile.collectAsState()
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
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Text(
                "پروفایل کاربری",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    // if user being in this destination - do not route them
                    if (navController.currentDestination?.route != Navigation.EditProfile.route) {
                        navController.navigate(Navigation.EditProfile.route)
                    }

                }) {
                    Image(
                        painter = painterResource(R.drawable.ediricon), "",
                        modifier = Modifier.size(40.dp)
                    )
                }

                Image(
                    painter = painterResource(R.drawable.userpicbigwithborder),
                    "",
                    modifier = Modifier.size(130.dp), contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.picicon), "",
                    modifier = Modifier.size(40.dp)
                )
            }
            if(showUsernameAndPhoneNumber){
                if(!profile.name.isEmpty() || !profile.phone.isEmpty()){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    profile.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    profile.phone,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
            }}}
        }
    }
}