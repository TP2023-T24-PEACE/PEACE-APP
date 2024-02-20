package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking2Destination

@OptIn(ExperimentalSwipeableCardApi::class)
@Destination
@Composable
fun Questions (
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

            var currentStep by remember { mutableStateOf(1) }
            StepIndicator(currentStep = currentStep, totalSteps = 5)
            val state = rememberSwipeableCardState()

            val questions = listOf(
                "1 How are you feeling today?",
                "2 How are you feeling today?",
                "3 How are you feeling today?",
                "4 How are you feeling today?",
                "5 How are you feeling today?"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .swipableCard(
                        state = state,
                        onSwiped = { direction ->
                            println("The card was swiped to $direction")
                            currentStep++

                        },
                        onSwipeCancel = {
                            println("The swiping was cancelled")
                            currentStep++
                        }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Cyan)
                        .border(
                            1.dp,
                            Color.Blue,
                            shape = RoundedCornerShape(15.dp),
                        )
                        .align(Alignment.Center)
                        .padding(16.dp)
                        // content alignment


                ) {
                    Text(
                        text = questions[0],
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        // vertical arrangement
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

        }
        BottomMenu(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(), navigator, DashboardDestination, Moodtracking2Destination)
    }

}