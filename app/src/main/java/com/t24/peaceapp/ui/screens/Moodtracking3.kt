package com.t24.peaceapp.ui.screens

import android.content.Context
import android.os.StrictMode
import android.widget.Toast
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
import com.ramcosta.composedestinations.spec.Direction
import com.t24.peaceapp.Mood
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking1Destination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking2Destination
import com.t24.peaceapp.ui.state.UpdateUserToken
import com.t24.peaceapp.ui.state.context
import khttp.post

fun post_entry(navigator: DestinationsNavigator): String {
    // TODO POST entry to server

    val sharedPref = context.getSharedPreferences("userId", Context.MODE_PRIVATE)
    val loggedInUserId = sharedPref.getString("userId", "")
    val sharedPrefToken = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val token = sharedPrefToken.getString("token", "")
    val overallMood = store.state.value
    val moods = store.state.moods
    val reasons = store.state.reasons

    println("user_id: $loggedInUserId")
    println("overall_mood: $overallMood")
    println("moods: $moods")
    println("reasons: $reasons")

    val authorization = "Bearer"+ (token?.replace("\"", "") ?: "")

    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    // format list mood into key value pairs
    var moodList = ""
    for (i in 0 until moods.size){
        if(i == moods.size - 1){
            moodList += "\"${moods[i]}\": 1"
        } else {
            moodList += "\"${moods[i]}\": 1,"
        }
    }

    var reasonList = ""
    for (i in 0 until reasons.size){
        if(i == reasons.size - 1){
            reasonList += "\"${reasons[i]}\": \"True\""
        } else {
            reasonList += "\"${reasons[i]}\": \"True\","
        }
    }

    val body = """
        {
            "type": "daily_entry",
            "user_id":$loggedInUserId,
            "overall_mood": $overallMood,
            "moods": {$moodList},
            "reasons": {$reasonList}
        }
    """.trimIndent()

    println(body)

    // Send async POST request to server
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "3a2455ba-9d37-467d-bff0-d5a830526066",
        "Authorization" to authorization

    )
    val response =  post("http://10.0.2.2:8000/api/v1/entries",
        headers = headers,
        data = body)


    println(response)

    return if(response.statusCode == 201){
         navigator.navigate(DashboardDestination)
        "Záznam bol úspešne uložený!"
    } else {
         navigator.navigate(DashboardDestination)
        "Nastala chyba pri ukladaní záznamu!"
    }
}
@Destination
@Composable
fun Moodtracking3(
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
            ),"Prečo sa takto cítiš?","reasons"
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
                    navigator.navigate(Moodtracking2Destination)
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
                        val result = post_entry(navigator)
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                        println(result)
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
fun BottomMenu(modifier: Modifier, navigator: DestinationsNavigator, prevDestination: Direction, nextDestination: Direction){
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding( 15.dp)

    ) {

        Image(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "Back Button",
            modifier = Modifier.clickable {
                navigator.navigate(prevDestination)
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
                    navigator.navigate(nextDestination)
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

