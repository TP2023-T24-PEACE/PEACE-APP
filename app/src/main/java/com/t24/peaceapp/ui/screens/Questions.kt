package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.state.UpdateAnswers


@OptIn(ExperimentalSwipeableCardApi::class)
@Destination
@Composable


fun Questions (
    navigator: DestinationsNavigator
) {
    var questions = store.state.questions
    val gradient = Brush.verticalGradient(
        0.0f to Color(0xBF4C5F18),
        1.0f to Color(0xBF2E9E6F),
        startY = 0.0f,
        endY = 1500.0f
    )
    Box(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            var currentStep by remember { mutableStateOf(1) }
            StepIndicator(currentStep = currentStep, totalSteps = 5 )
            val state1 = rememberSwipeableCardState()
            val state2 = rememberSwipeableCardState()
            val state3 = rememberSwipeableCardState()
            val state4 = rememberSwipeableCardState()
            val state5 = rememberSwipeableCardState()

            // stack of 5 questions on top of each other

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 40.dp)
                    .fillMaxSize()
                    .swipableCard(
                        state = state1,
                        onSwiped = { it ->
                            currentStep++;
                            // prinln direction of swipe
                            println("swiped: $it");
                            // if swiped to the right, add "True" to the answerList
                            val question = questions[0]
                            val answer = Pair(question, it.toString() == "Right")
                            println("answerList: $answer");
                            store.dispatch(UpdateAnswers(answer))
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
                ) {
                    Text(
                        text = questions[0].toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .swipableCard(
                        state = state2,
                        onSwiped = { it ->
                            currentStep++;
                            // prinln direction of swipe
                            println("swiped: $it");
                            // if swiped to the right, add "True" to the answerList
                            val question = questions[1]
                            val answer = Pair(question, it.toString() == "Right")
                            println("answerList: $answer");
                            store.dispatch(UpdateAnswers(answer))
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
                ) {
                    Text(
                        text = questions[1].toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .swipableCard(
                        state = state3,
                        onSwiped = { it ->
                            currentStep++;
                            // prinln direction of swipe
                            println("swiped: $it");
                            // if swiped to the right, add "True" to the answerList
                            val question = questions[2]
                            val answer = Pair(question, it.toString() == "Right")
                            println("answerList: $answer");
                            store.dispatch(UpdateAnswers(answer))
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
                ) {
                    Text(
                        text = questions[2].toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .swipableCard(
                        state = state4,
                        onSwiped = { it ->
                            currentStep++;
                            // prinln direction of swipe
                            println("swiped: $it");
                            // if swiped to the right, add "True" to the answerList
                            val question = questions[3]
                            val answer = Pair(question, it.toString() == "Right")
                            println("answerList: $answer");
                            store.dispatch(UpdateAnswers(answer))
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
                ) {
                    Text(
                        text = questions[3].toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .swipableCard(
                        state = state5,
                        onSwiped = { it ->
                            currentStep++;
                            // prinln direction of swipe
                            println("swiped: $it");
                            // if swiped to the right, add "True" to the answerList
                            val question = questions[4]
                            val answer = Pair(question, it.toString() == "Right")
                            println("answerList: $answer");
                            store.dispatch(UpdateAnswers(answer))
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
                ) {
                    Text(
                        text = questions[4].toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

        }
        Text(
            text = "Prstom potiahni doľava ak nesúhlasíš, doprava ak súhlasíš",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            color = Color.White,
            // put over the bottom of the screen
            modifier = Modifier
                .width(250.dp)
                .padding(bottom = 125.dp)
                .align(Alignment.BottomCenter)

        )
        BottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            navigator,
            DashboardDestination,
            DashboardDestination
        )
    }

}
