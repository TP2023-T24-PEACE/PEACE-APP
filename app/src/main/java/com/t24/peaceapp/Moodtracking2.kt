package com.t24.peaceapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Moodtracking2() {

    var selectedMoods by remember { mutableStateOf(listOf<Mood>()) }

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
            MoodSelector(moods =
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
                selectedMoods = selectedMoods,
                onMoodSelected = { mood ->
                    selectedMoods = if (selectedMoods.contains(mood)) {
                        selectedMoods.filter { it != mood }
                    } else {
                        selectedMoods + mood
                    }
                }
            )
        }
        BottomMenu(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth(),
            selectedMoods = selectedMoods,
            onButtonClicked = {
                // Handle the button click here, for example, print the selected moods
                selectedMoods.forEach { mood ->
                    println("Selected Mood: ${mood.title}")
                }
            }
        )
    }

}



@Composable
fun BottomMenu(modifier: Modifier,
               selectedMoods: List<Mood>,
               onButtonClicked: () -> Unit){
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
            .clickable(onClick = onButtonClicked)
        ){

            Text(text = "Pokračovať", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        }
    }

}


@Composable
fun MoodSelector(moods: List<Mood>,
                 selectedMoods: List<Mood>,
                 onMoodSelected: (Mood) -> Unit
                 ){

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 110.dp)
    ) {

        Text(text = "Pomôž nám lepšie pochopiť tvoju náladu",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp),
            modifier = Modifier.fillMaxHeight())
        {
            items(moods.size){index ->
                val mood = moods[index]
                val isSelected = selectedMoods.contains(mood)
                MoodItem(
                    mood = mood,
                    isSelected = isSelected,
                    onMoodSelected = { onMoodSelected(mood) }
                )
            }
        }


    }
}


//@Composable
//fun MoodItem(
//    mood: Mood,
//    isSelected: Boolean,
//    onMoodSelected: () -> Unit
//){
////    var isSelected by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier
//        .padding(6.dp)
//        .fillMaxSize()
//        .clip(RoundedCornerShape(20.dp))
//        .background(
//            if (isSelected) Color(0xFF64AD41) else Color(0xFF94CC79)
//        )
//        .padding(15.dp)
//        .clickable {
//           isSelected = !isSelected;
//        }
//        .aspectRatio(1f)
//
//    ) {
//        Image(
//            painter = painterResource(id = mood.iconId),
//            contentDescription = mood.title,
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .size(56.dp)
//                .padding(bottom = 4.dp)
//        )
//        Text(
//            text = mood.title,
//            modifier = Modifier.align(Alignment.BottomCenter),
//            style = MaterialTheme.typography.bodyLarge,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold
//        )
//
//    }
//}


@Composable
fun MoodItem(
    mood: Mood,
    isSelected: Boolean,
    onMoodSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) Color(0xFF64AD41) else Color(0xFF94CC79)
            )
            .padding(15.dp)
            .clickable(onClick = onMoodSelected)
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(id = mood.iconId),
            contentDescription = mood.title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(56.dp)
                .padding(bottom = 4.dp)
        )
        Text(
            text = mood.title,
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

