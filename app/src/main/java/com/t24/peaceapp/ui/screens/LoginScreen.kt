package com.t24.peaceapp.ui.screens

import android.os.StrictMode
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
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.RegisterScreenDestination
import com.t24.peaceapp.ui.state.UpdateUserId
import com.t24.peaceapp.ui.state.UpdateUserToken
import khttp.get as khttp_get
import khttp.post as khttp_post

fun getUserId(token: String): String {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    println("GETTING USER ID")
    // remove quotes from token
    val authorization = "Bearer"+token.replace("\"", "")
    // Register user
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "3a2455ba-9d37-467d-bff0-d5a830526066",
        "Authorization" to authorization
    )
    val response =  khttp_get("http://10.0.2.2:8000/api/v1/user-me",
        headers = headers)
    println("header before user-me")
    println(headers)
    println(response)

    return if(response.statusCode == 200){
        response.statusCode.toString()
        val userId = response.text.split("\"id\":")[1].split(",")[0]
        println("userId: $userId")
        // Update userId in state
        store.dispatch(UpdateUserId(userId))
        userId
    } else {
        // If successful, navigate to Dashboard
        "Nesprávne prihlasovacie údaje!"
    }
}

fun login(username : String, password : String, navigator: DestinationsNavigator): String {

    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    println("LOGING IN USER")
    // Register user
    if(username.isEmpty() || password.isEmpty()){
        return "Všetky polia musia byť vyplnené!"
    }

    val body = """
        {
            "email": "$username",
            "password": "$password"
        }
    """.trimIndent()

    println(body)

    // Send async POST request to server
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "3a2455ba-9d37-467d-bff0-d5a830526066"
    )
    val response =  khttp_post("http://10.0.2.2:8000/api/v1/auth",
        headers = headers,
        data = body)


    println(response)

    return if(response.statusCode == 200){
        response.statusCode.toString()
        val token = response.text.split("\"token\":")[1].split(",")[0]
        println("token: $token")
        // Update userId in state
        store.dispatch(UpdateUserToken(token))
        navigator.navigate(DashboardDestination)
        "Prihlásenie prebehlo úspešne!"

        getUserId(token)

    } else {
        // If successful, navigate to Dashboard
        "Nesprávne prihlasovacie údaje!"
    }
}

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
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
            text = "Prihlásenie",
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
                onValueChange = { password = it },
                textStyle = TextStyle(color = Color.Black),
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
            Button(onClick = {
                // Log output of register to console
                val result = login(username, password, navigator)
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                println(result)
            },
                contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                elevation = ButtonDefaults.buttonElevation(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5CDB5C),
                    contentColor = Color.White),
                ) {
                Text("Prihlásiť sa", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            // 1px divider
            Divider(
                color = Color(0xFFAFAFAF),
                thickness = 1.dp,
                modifier = Modifier.width(280.dp)
            )
            Text(
                text = "Ešte nemáte účet?",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 16.dp)
            )
            // Register button
            OutlinedButton(onClick = {
                navigator.navigate(RegisterScreenDestination)
            }
                ,
                contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                elevation = ButtonDefaults.buttonElevation(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF5CDB5C))
                ) {
                Text("Zaregistrujte sa", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}