package com.t24.peaceapp.ui.screens


import android.content.Context
import android.os.StrictMode
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.composables.PriorityOfTheWeek
import com.t24.peaceapp.ui.screens.destinations.AnalysisScreenDestination
import com.t24.peaceapp.ui.screens.destinations.LoginScreenDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking1Destination
import com.t24.peaceapp.ui.screens.destinations.QuestionsDestination
import com.t24.peaceapp.ui.state.UpdateQuestions
import com.t24.peaceapp.ui.state.UpdateUserId
import com.t24.peaceapp.ui.state.UpdateUserEmail
import com.t24.peaceapp.ui.state.UpdateUserLevel
import com.t24.peaceapp.ui.state.UpdateUserXP
import com.t24.peaceapp.ui.state.context
import khttp.get

fun getUserId(token: String) {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    println("GETTING USER ID")
    // remove quotes from token
    val authorization = "Bearer"+token.replace("\"", "")
    // Register user
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "30fa4be8-f8bb-4131-80bb-eda62eb9d116",
        "Authorization" to authorization
    )
    val response =  get("https://tp-be-production.up.railway.app/api/v1/user-me",
        headers = headers)
    println("header before user-me")
    println(headers)
    println(response)

    if(response.statusCode == 200){
        response.statusCode.toString()
        val userId = response.text.split("\"id\":")[1].split(",")[0]
        val email = response.text.split("\"email\":")[1].split(",")[0]
        val xp = response.text.split("\"xp\":")[1].split(",")[0].replace(" ", "")
        val level = response.text.split("\"level\":")[1].split(",")[0].replace(" ", "")
        println("userId: $userId")
        println("email: $email")
        println("xp: $xp")
        println("level: $level")
        store.dispatch(UpdateUserId(userId))
        store.dispatch(UpdateUserEmail(email))
        store.dispatch(UpdateUserXP(xp.toInt()))
        store.dispatch(UpdateUserLevel(level.toInt() + 1))
        userId
    }
}

fun getQuestions(): List<String> {


    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    println("Fetching questions")

    val sharedPrefToken = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val loggedInUserToken = sharedPrefToken.getString("token", "")
    val authorization = "Bearer"+ (loggedInUserToken?.replace("\"", ""))
    if (loggedInUserToken != null) {
        getUserId(loggedInUserToken)
    }

    // Send async POST request to server
    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "30fa4be8-f8bb-4131-80bb-eda62eb9d116",
        "Authorization" to authorization
    )

    println("Headers: $headers")

    val response =  get("https://tp-be-production.up.railway.app/api/v1/tinder-get",
        headers = headers)


    println(response)
    val items = response.jsonObject.getJSONArray("items")

    val questions = mutableListOf<String>()

    // Extract questions from the JSON array
    for (i in 0 until items.length()) {
        val questionObj = items.getJSONObject(i)
        val question = questionObj.getString("question")
        questions.add(question.toString())
    }

    println(questions)
    if(response.statusCode == 200){
        response.statusCode.toString()
        return questions
    } else {
        return questions
    }
}

@Destination
@Composable
fun Dashboard(navigator: DestinationsNavigator){
    // save questions into state
    store.dispatch(UpdateQuestions(getQuestions()))

    val gradient = Brush.verticalGradient(
        0.0f to Color(0xBF4C5F18),
        1.0f to Color(0xBF2E9E6F),
        startY = 0.0f,
        endY = 1500.0f
    )

    println(store.getState().userId)



    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ){

        val email = store.state.email

        Column {

            InfoPanel()

            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                DailyTasks(navigator)
                Last7days(navigator)
                PriorityOfTheWeek()
                // Logout button
                Text(text = "Si prihlásený ako: $email",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(20.dp)
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFDC3545))
                        .padding(16.dp)
                        .clickable {
                            println("Logging out")
                            store.dispatch(UpdateUserId(""))
                            store.dispatch(UpdateUserEmail(""))
                            navigator.navigate(LoginScreenDestination)
                        }
                ) {
                    Text(
                        text = "Odhlásiť sa",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }

}


@Composable
fun InfoPanel() {
    val xp = store.state.xp.toString().toInt()
    val level = store.state.level.toString().toInt()
    val xp_current = (xp - ((level-1)*100)).toFloat()/100.toFloat()
    println("XP current: $xp_current")

        Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .shadow(16.dp)
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
            .background(Color(0xFF314700))
            .padding(horizontal = 24.dp, vertical = 12.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Row {
                PreviewProgressBar(xp_current.toFloat(), Color(0xFF4DB6AC), Color(0xFF90A4AE), 4.dp)
                Text(
                    text = "$level",
                    modifier = Modifier.offset(x = -27.dp, y =3.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                Text(
                    text = "Domov",
                    modifier = Modifier.offset(x = -24.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Column {

            }
        }
    }
}

@Composable
fun ComposeCircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    fillColor: Color,
    backgroundColor: Color,
    strokeWidth: Dp
) {
    Canvas(
        modifier = modifier
            .size(40.dp)
    ) {
        // Background Line
        drawArc(
            color = backgroundColor,
            140f,
            260f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = fillColor,
            140f,
            percentage * 260f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )


        var angleInDegrees = (percentage * 260.0) + 50.0
        var radius = (size.height / 2)
        var x = -(radius * Math.sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        var y = (radius * Math.cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

        drawCircle(
            color = Color.White,
            radius = 5f,
            center = Offset(x,  y)
        )
    }
}
@Composable
fun PreviewProgressBar(percentage: Float, fillColor: Color, backgroundColor: Color, strokeWidth: Dp) {
    ComposeCircularProgressBar(
        percentage = percentage,
        fillColor = fillColor,
        backgroundColor = backgroundColor,
        strokeWidth = strokeWidth
    )
}

@Composable
fun DailyTasks(
    navigator: DestinationsNavigator
){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 0.dp)
    ) {
        Text(
            text = "Tvoje úlohy na dnes",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 8.dp)
            .fillMaxWidth()
    )
    {

        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .shadow(16.dp)
                .background(
                    brush = Brush.linearGradient(
                        0.0f to Color(0xFF2F9C95),
                        500.0f to Color(0xFF314700),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
                .padding(16.dp)
                .clickable {
                    navigator.navigate(Moodtracking1Destination)
                }

        ) {

            Image(painter = painterResource(id = R.drawable.mood_records), contentDescription = "Mood Records",
                modifier = Modifier.size(96.dp)
            )
            Text(text = "Záznam nálady", style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold, fontSize = 14.sp
//                modifier = Modifier.padding(top = 10.dp)
                )
//            Image(painter = painterResource(id = R.drawable.questionmark), contentDescription = "Questions")
//

        }

        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .shadow(16.dp)
                .background(
                    brush = Brush.linearGradient(
                        0.0f to Color(0xFF25B45E),
                        500.0f to Color(0xFF3B5307),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
                .clip(RoundedCornerShape(10.dp))
                .padding(16.dp)

        ) {
            Image(
                painter = painterResource(
                id = R.drawable.questionmark),
                contentDescription = "Questions",
                modifier = Modifier.clickable {
                    navigator.navigate(QuestionsDestination)
                }
            )
            Text(text = "Otázky", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, fontSize = 14.sp)

        }

    }

}


@Composable
fun Last7days(
    navigator: DestinationsNavigator
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 0.dp)
            .fillMaxWidth()

    )
    {
        Text(
            text = "Posledných 7 dní",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 4.dp)
            .fillMaxWidth()
            .aspectRatio(2f)
            .clickable {
//                println("Navigating to AnalysisScreenDestination")
//                println(store.getState().userId)
                navigator.navigate(AnalysisScreenDestination)
            }
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))

        ) {
            Image(painter = painterResource(id = R.drawable.mood_graph), contentDescription = "Last 7 Days Graph",
                modifier = Modifier.fillMaxSize()
                )
        }
    }



}



@Composable
fun MoodAnalysis(
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 0.dp)
            .fillMaxWidth()
    )
    {
        Text(
            text = "Analýza tvojej nálady",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 8.dp)
            .fillMaxWidth()
            .aspectRatio(2f)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        0.0f to Color(0xFF0C9442),
                        500.0f to Color(0xFF3B5307),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Adjust the padding as needed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sleep_emoji),
                    contentDescription = "Sleep",

                )
//                Spacer(modifier = Modifier.width(8.dp)) // Adjust the spacing between icon and text
                Text(
                    text = "Analýza",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

