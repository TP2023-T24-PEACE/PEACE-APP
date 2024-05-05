package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.Mood
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking1Destination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking3Destination

@Destination
@Composable
fun Moodtracking2(
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
            ),"Pomôž nám lepšie pochopiť tvoju náladu","moods"
            )
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
                    navigator.navigate(Moodtracking1Destination)
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
                        navigator.navigate(Moodtracking3Destination)
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



