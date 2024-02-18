package com.t24.peaceapp.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.t24.peaceapp.R


@OptIn(ExperimentalMaterial3Api::class)
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
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(50.dp, 0.dp, 0.dp, 50.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.male),
                    contentDescription = "Male",
                    modifier = Modifier.width(50.dp).height(50.dp))
            }
            Button(
                onClick = {gender = "non-binary"},
                modifier = Modifier.weight(0.5f),
                colors = if(gender == "non-binary") {colorsActive} else {colorsInactive},
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(0.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nonbinary),
                    contentDescription = "Non-binary",
                    modifier = Modifier.width(50.dp).height(50.dp))
            }
            Button(
                onClick = {gender = "female"},
                modifier = Modifier.weight(0.5f),
                colors = if(gender == "female") {colorsActive} else {colorsInactive},
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(0.dp, 50.dp, 50.dp, 0.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.female),
                    contentDescription = "Female",
                    modifier = Modifier.width(50.dp).height(50.dp))
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
        Spacer(modifier = Modifier.weight(0.01f))
        Demo_ExposedDropdownMenuBox()
        }

    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val coffeeDrinks = arrayOf(
        "Aký je tvoj dôvod stiahnutia aplikácie?",
        "Chcem sa cítiť lepšie",
        "Chcem byť produktívnejší",
        "Chcem si zlepšiť spánok",
        "Chcem sa zbaviť zlých návykov"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
