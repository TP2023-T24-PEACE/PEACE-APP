package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking2Destination
//import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
//import com.t24.peaceapp.ui.screens.destinations.Moodtracking2Destination

@Destination
@Composable
fun Moodtracking1 (
    navigator: DestinationsNavigator
) {

    val gradient = Brush.verticalGradient(
        0.0f to Color(0xBF4C5F18),
        1.0f to Color(0xBF2E9E6F),
        startY = 0.0f,
        endY = 1500.0f
    )
    Box (
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
    ){
        Column {
            StepIndicator(currentStep = 1, totalSteps = 3)
            MoodSlider()

        }
        BottomMenu(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(), navigator, DashboardDestination, Moodtracking2Destination)


    }

}



@Composable
fun MoodSlider(){
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    val moodImages = listOf(
        R.drawable.green_excited,
        R.drawable.yellow_happy,
        R.drawable.lightorange_neutral,
        R.drawable.orange_sad,
        R.drawable.red_verysad
    )


    val selectedImageIndex = when (sliderPosition) {
        in 0.0f..0.2f -> 4
        in 0.2f..0.4f -> 3
        in 0.4f..0.6f -> 2
        in 0.6f..0.8f -> 1
        in 0.8f..1.0f -> 0
        else -> 0
    }



    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {

                val selectedImageId = moodImages[selectedImageIndex]
                Image(
                    painter = painterResource(id = selectedImageId),
                    contentDescription = "Excited",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(0.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Ako sa práve cítiš?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp)

                )

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(15.dp)

                ) {

                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it }
                    )
                    Text(text = sliderPosition.toString(), modifier = Modifier.padding(top = 35.dp))
                }
            }
        }
    }

}