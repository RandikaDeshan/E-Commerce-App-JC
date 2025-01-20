package com.example.ecommerceapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.data.PaymentStatus
import com.example.ecommerceapp.presentation.StripeViewModel

@Composable
fun PaymentScreen(viewModel: StripeViewModel,navController: NavController){
    val paymentStatus by viewModel.paymentStatus.collectAsState()
    var name by remember {
        mutableStateOf("Varat Singh Sharma")
    }
    var cardnumber by remember {
        mutableStateOf("4747  4747  4747  4747")
    }
    var date by remember {
        mutableStateOf("07/21")
    }
    var cvc by remember {
        mutableStateOf("474")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp).verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {navController.popBackStack()}
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
            Text(
                text = "Credit / Debit card"
            )
            Text(
                text = "  "
            )
        }
        Image(
            painter = painterResource(id = R.drawable.card),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(240.dp)
        )
        Row (
            modifier = Modifier.fillMaxWidth().padding(bottom = 35.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = "",
                tint = Color.Gray,
            )
        }
        Text(
            text = "Name on card",
            fontSize = 14.sp,
            fontWeight = FontWeight(900),
            modifier = Modifier.padding(bottom = 13.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            value = name,
            onValueChange = {
                name = it
            },
            shape = RoundedCornerShape(10.dp)
        )
        Text(
            text = "Card number",
            fontSize = 14.sp,
            fontWeight = FontWeight(900),
            modifier = Modifier.padding(bottom = 13.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            value = cardnumber,
            onValueChange = {
                cardnumber = it
            },
            trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.symbol),
                        contentDescription = "",
                        modifier = Modifier.size(height = 20.dp, width = 17.dp)
                    )
            },
            shape = RoundedCornerShape(10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 43.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = "Expiry date",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                    modifier = Modifier.padding(bottom = 13.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = date,
                    onValueChange = {
                        date = it
                    },
                    shape = RoundedCornerShape(10.dp)
                )
            }
            Spacer(Modifier.padding(22.dp))
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = "CVC",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900),
                    modifier = Modifier.padding(bottom = 13.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = cvc,
                    onValueChange = {
                        cvc = it
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.hint),
                            contentDescription = "",
                            modifier = Modifier.size(height = 26.dp, width = 36.dp)
                        )
                    },
                    shape = RoundedCornerShape(10.dp)
                )
            }

        }
        Button(
            onClick = {navController.navigate("success_page")},
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
            Text(
                text = "USE THIS CARD",
                fontSize = 11.sp,
                fontWeight = FontWeight(900)
            )
        }
    }
}