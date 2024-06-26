package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t24.peaceapp.Mood
import com.t24.peaceapp.ui.state.UpdateMoods
import com.t24.peaceapp.ui.state.UpdateReasons


fun updateMoods(moods: List<Mood>) {
    // Create empty list that takes any list
    val moodsFiltered = mutableListOf<Any>()
    for (mood in moods) {
        if(mood.selected){
            moodsFiltered.add(mood.title)
        }
    }
    println("moodsFiltered: $moodsFiltered")
    store.dispatch(UpdateMoods(moodsFiltered))
}

fun updateReasons(moods: List<Mood>) {
    // Create empty list that takes any list
    val reasonsFiltered = mutableListOf<Any>()
    for (reason in moods) {
        if(reason.selected){
            reasonsFiltered.add(reason.title)
        }
    }
    println("reasonsFiltered: $reasonsFiltered")
    store.dispatch(UpdateReasons(reasonsFiltered))
}

@Composable
fun MoodReasonSelector(moods: List<Mood>,
                       title: String,
                       type: String
){

    var moods = moods

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 110.dp)
    ) {

        Text(text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp),
            modifier = Modifier.fillMaxHeight())
        {
            items(moods.size){

                MoodReason(
                    mood = moods[it],
                    onValueChanged = {
                        mood ->
                        if(type == "moods"){
                            moods = moods.mapIndexed { index, moodItem ->
                                if (index == it) {
                                    val moodLocal = moodItem.copy(selected = mood);
                                    moodLocal
                                } else {
                                    moodItem
                                }
                            }
                            updateMoods(moods)
                        } else if (type == "reasons") {
                            moods = moods.mapIndexed { index, moodItem ->
                                if (index == it) {
                                    val moodLocal = moodItem.copy(selected = mood);
                                    moodLocal
                                } else {
                                    moodItem
                                }

                            }
                            updateReasons(moods)
                        }
                    }
                )
            }
        }


    }
}
