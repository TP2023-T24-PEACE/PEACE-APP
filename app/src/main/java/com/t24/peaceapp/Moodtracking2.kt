package com.t24.peaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Moodtracking2() {

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

        Column{
            StepIndicator(currentStep = 2, totalSteps = 3)
            MoodReasonSelector(moods =
            listOf(
                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),

                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Štastný",
                    iconId = R.drawable.smiling_face,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Vyčerpaný",
                    iconId = R.drawable.exhausted_face,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Smutný",
                    iconId = R.drawable.crying_face,
                    moodValue = 4,
                    selected = false
                )
            ),
                "Pomôž nám lepšie pochopiť tvoju náladu"

            )
        }
        BottomMenu(modifier = Modifier.align(Alignment.BottomCenter). fillMaxWidth())


    }

}



