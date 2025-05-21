package com.swaz.notebook.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.swaz.notebook.R
import com.swaz.notebook.data.AuthView
import com.swaz.notebook.nav.Route_Lgn
import com.swaz.notebook.nav.Route_Reg


@Composable
fun Reg(
    navController: NavController,
)
{
    val authView : AuthView = viewModel()
    val onReg : () -> Unit
    val onTouch : () -> Unit
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            text = "Welcome to NoteBook. Please Register to continue",
            color = Color.Black,
            fontSize = 50.sp,
            fontFamily = FontFamily.Cursive

        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.n),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Don't have an Account? ",
            color = Color.Black,
            fontSize = 20.sp,

            )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"

                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                authView.register(email, password)
                { success ->
                    if (
                        success
                    )
                    {
                        onReg()
                        navController.navigate(Route_Lgn)
                    }

                }

            },
            modifier = Modifier
                .padding(10.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Green
            )
        ) {
            Text("Register", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = { navController.navigate(Route_Lgn) }) {
            Text(
                text = "Already have an account? Login ",
                fontSize = 20.sp

            )
        }
    }

}