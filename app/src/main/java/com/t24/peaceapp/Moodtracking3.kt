package com.t24.peaceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun Moodtracking3() {

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
            StepIndicator(currentStep = 3, totalSteps = 3)
            MoodReasonSelector(moods =
            listOf(
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
                Mood(
                    title = "Financie",
                    iconId = R.drawable.finance,
                    moodValue = 2,
                    selected = false
                ),
                Mood(
                    title = "Rodina",
                    iconId = R.drawable.family,
                    moodValue = 3,
                    selected = false
                ),
                Mood(
                    title = "Práca",
                    iconId = R.drawable.work,
                    moodValue = 4,
                    selected = false
                ),
            ),
                "Prečo sa takto cítiš?"
            )
        }
        BottomMenu(modifier = Modifier.align(Alignment.BottomCenter). fillMaxWidth())
    }

}



@Composable
fun BottomMenu(modifier: Modifier,
               ){
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding( 15.dp)

    ) {

        Image(painter = painterResource(id = R.drawable.arrow_left), contentDescription = "Back Button")

        Box(modifier = Modifier
            .padding(10.dp)
//            .width(250.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFF5CDB5C))
            .padding(horizontal = 30.dp, vertical = 12.dp)

        ){

            Text(text = "Pokračovať", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        }
    }

}


