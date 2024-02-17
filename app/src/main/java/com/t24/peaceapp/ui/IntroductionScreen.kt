package com.t24.peaceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun IntroductionScreen(
) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    var colorsInactive = ButtonDefaults.buttonColors(containerColor = Color.White)
    var colorsActive = ButtonDefaults.buttonColors(containerColor = Color(0xFF64AD41))


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
        Spacer(modifier = Modifier.weight(0.015f))
        Text(
            text = "Povedz nám niečo o sebe...",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.weight(0.01f))
        TextField(
                value = name,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.width(300.dp),
                onValueChange = { name = it },
                label = { Text(
                    text = "Krstné meno",
                    style = MaterialTheme.typography.bodyMedium,
                ) },
                shape = MaterialTheme.shapes.extraLarge,
            )
        Spacer(modifier = Modifier.weight(0.01f))
        Row(
            modifier = Modifier.width(300.dp)
        ){
            /* Gender */
            Button(
                onClick = {gender = "male"},
                modifier = Modifier.weight(0.5f),
                colors = if(gender == "male") {colorsActive} else {colorsInactive},
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(50.dp, 0.dp, 0.dp, 50.dp),
            ) {
                Text(
                    text = "Muž",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Button(
                onClick = {gender = "non-binary"},
                modifier = Modifier.weight(0.5f),
                colors = if(gender == "non-binary") {colorsActive} else {colorsInactive},
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(0.dp),
            ) {
                Text(
                    text = "Nebinárne",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Button(
                onClick = {gender = "female"},
                modifier = Modifier.weight(0.5f),
                colors = if(gender == "female") {colorsActive} else {colorsInactive},
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(0.dp, 50.dp, 50.dp, 0.dp),
            ) {
                Text(
                    text = "Žena",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.01f))
        TextField(
            value = age,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.width(300.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { age = it },
            label = { Text(
                text = "Vek",
                style = MaterialTheme.typography.bodyMedium,
            ) },
            shape = MaterialTheme.shapes.extraLarge,
        )
        }

    }
