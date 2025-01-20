package com.example.ecommerceapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignInPage(navController: NavController,authViewModel: AuthService) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home_screen") {
                popUpTo(0)
            }
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 30.dp, end = 30.dp).verticalScroll(
            rememberScrollState()
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Geeta.",
            color = Color.Black,
            fontSize = 44.sp,
            fontWeight = FontWeight(900)
        )
        Text(
            text = "Sign In",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(top = 20.dp, bottom = 70.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            textStyle = TextStyle(
                color = Color.Black
            ),
            onValueChange = {
                email = it
            },
            label = {
                Text("Email", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(10.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp),
            value = password,
            textStyle = TextStyle(
                color = Color.Black
            ),
            onValueChange = {
                password = it
            },
            label = {
                Text("Password", color = Color.Gray)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(10.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        Button(
            onClick = {
                authViewModel.login(email, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier.size(width = 100.dp, height = 50.dp).border(width = 1.dp, shape = RoundedCornerShape(50.dp), color = Color.Black)
        ) {
            Text(
                text = "SIGN IN",
                color = Color.Black,
                fontWeight = FontWeight(900),
                fontSize = 11.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 20.dp),
        ) {
            Text(
                text = "Don't have an account?",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                modifier = Modifier.padding(end = 15.dp)
            )
            Text(
                text = "Sign Up",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight(900),
                modifier = Modifier.clickable {
                     navController.navigate("sign_up")      {
                         popUpTo(0)
                     }          }
            )
        }
    }
}

