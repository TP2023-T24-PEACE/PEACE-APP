package com.t24.peaceapp.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking2Destination
import com.t24.peaceapp.ui.state.UpdateSlider
import com.t24.peaceapp.ui.state.context

fun saveSliderPosition(sliderPosition: Float, navigator: DestinationsNavigator) {
    println("Mood state saved: $sliderPosition")

    val sharedPref = context.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val loggedInUserId = sharedPref.getString("userId", "")?.replace("\"", "")?.replace(" ", "")
    println("userId from sharedPref / Moodtracking1: $loggedInUserId")

    store.dispatch(UpdateSlider(sliderPosition))

    navigator.navigate(Moodtracking2Destination)
}

@Destination
@Composable
fun Moodtracking1 (
    navigator: DestinationsNavigator
) {
    var sliderPosition by remember { mutableFloatStateOf(0.5f) }
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
            MoodSlider(onValueChanged = { sliderPosition = it }, sliderPosition = sliderPosition)
        }
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding( 15.dp)
                .align(Alignment.BottomCenter).fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Back Button",
                modifier = Modifier.clickable {
                    navigator.navigate(DashboardDestination)
                }
            )

            Box(modifier = Modifier
                .padding(10.dp)
//            .width(250.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF5CDB5C))
                .padding(horizontal = 30.dp, vertical = 12.dp)

            ){
                Button(
                    contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                    elevation = ButtonDefaults.buttonElevation(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5CDB5C),
                        contentColor = Color.White),
                    onClick = {
                        saveSliderPosition(sliderPosition, navigator)
                    }) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = "POKRAČOVAŤ",
                        color = Color.White)
                    Spacer(
                        modifier = Modifier.weight(0.01f)
                    )
                }
            }
        }
    }
}



@Composable
fun MoodSlider(sliderPosition: Float, onValueChanged: (Float) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(sliderPosition) }

    val moodImages = listOf(
        R.drawable.emoji_100,
        R.drawable.emoji_84,
        R.drawable.emoji_67,
        R.drawable.emoji_50,
        R.drawable.emoji_34,
        R.drawable.emoji_17
    )


    val selectedImageIndex = when (sliderPosition) {
        in 0.0f..0.17f -> 5
        in 0.17f..0.34f -> 4
        in 0.34f..0.50f -> 3
        in 0.50f..0.67f -> 2
        in 0.67f..0.84f -> 1
        in 0.84f..1f -> 0
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
                        onValueChange = {
                            sliderPosition = it
                            onValueChanged(it)
                        },
                    )
                }
            }
        }
    }

}