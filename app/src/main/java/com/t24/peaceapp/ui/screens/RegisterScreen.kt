package com.t24.peaceapp.ui.screens

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.LoginScreenDestination
import com.t24.peaceapp.ui.state.UpdateUserId
import khttp.post as khttp_post


fun register(username : String, password : String, passwordAgain : String, navigator: DestinationsNavigator): String {

    val policy = ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    println("REGISTERING USER")
    // Register user
    if(username.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()){
        return "Všetky polia musia byť vyplnené!"
    }
    if(password != passwordAgain){
        return "Heslá sa nezhodujú!"
    }

    val name = "Test Name"
    val surname = "Test Surname"
    val sex = "male"
    val birthDate = "2000-10-21"

    val body = """
        {
            "email": "$username",
            "password": "$password",
            "name": "$name",
            "surname": "$surname",
            "sex": "$sex",
            "birth_date": "$birthDate"
        }
    """.trimIndent()

    println(body)

    // Send async POST request to servers
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "30fa4be8-f8bb-4131-80bb-eda62eb9d116",
    )
    val response =  khttp_post("https://tp-be-production.up.railway.app/api/v1/users",
        headers = headers,
        data = body)

    println(response)

    return if(response.statusCode != 201){
        response.statusCode.toString()

    } else {
        navigator.navigate(LoginScreenDestination)
        "Registrácia prebehla úspešne!"
    }
}
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
) {

    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordAgain by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4C5F18), Color(0xFF2E9E6F)
                    ),
                    tileMode = TileMode.Repeated
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Login form
        Text(
            text = "Registrácia",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                textStyle = TextStyle(color = Color.Black),
                label = {
                    Text(
                        text ="Prihlasovacie meno",
                        fontSize = 12.sp,
                        color = Color.Black
                        ) },
                singleLine = true)
            TextField(
                value = password,
                textStyle = TextStyle(color = Color.Black),
                onValueChange = { password = it },
                label = {
                    Text(
                        text ="Heslo",
                        fontSize = 12.sp,
                        color = Color.Black
                    ) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        R.drawable.hide
                    else
                        R.drawable.show

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = description
                        )
                    }
                }
            )
            TextField(
                value = passwordAgain,
                onValueChange = { passwordAgain = it },
                textStyle = TextStyle(color = Color.Black),

                label = {
                    Text(
                        text ="Heslo znova",
                        fontSize = 12.sp,
                        color = Color.Black
                    ) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Button(
                onClick = {
                    // Log output of register to console
                    val result = register(username, password, passwordAgain, navigator)
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                    println(result)
                },
                contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                elevation = ButtonDefaults.buttonElevation(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5CDB5C),
                    contentColor = Color.White
                ),
            ) {
                Text("Zaregistrovať sa", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            // 1px divider
            Divider(
                color = Color(0xFFAFAFAF),
                thickness = 1.dp,
                modifier = Modifier.width(280.dp)
            )
            Text(
                text = "Už máte vytvorený účet?",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 16.dp)
            )
            // Register button
            OutlinedButton(onClick = {
                navigator.navigate(LoginScreenDestination)
            },
                contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                elevation = ButtonDefaults.buttonElevation(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF5CDB5C))
                ) {
                Text("Prihláste sa", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}