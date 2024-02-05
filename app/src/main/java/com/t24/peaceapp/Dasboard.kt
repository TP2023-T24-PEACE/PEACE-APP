package com.t24.peaceapp


import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun Dashboard(){
    val gradient = Brush.verticalGradient(
        0.0f to Color(0xBF4C5F18),
        1.0f to Color(0xBF2E9E6F),
        startY = 0.0f,
        endY = 1500.0f
    )
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ){

        Column {

            InfoPanel()

            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                DailyTasks()
                Last7days()
                PriorityOfTheMonth()
                MoodAnalysis()
            }

        }
    }

}


@Composable
fun InfoPanel(){
    Box(modifier = Modifier
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

            Column {
                Text(text = "2", style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold))

            }
            Column {
                Text(text = "Domov", style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold))
            }

            Column {
                Icon(painter = painterResource(id = R.drawable.settings), contentDescription = "Settings" )
            }
        }    }
}



@Composable
fun DailyTasks(){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 0.dp)
    ) {
        Text(
            text = "Tvoje úlohy na dnes",
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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

        ) {
            Icon(painter = painterResource(id = R.drawable.mood_records), contentDescription = "Mood Records", tint = Color.White)
            Text(text = "Záznam nálady", style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))

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
            Icon(painter = painterResource(id = R.drawable.questionmark), contentDescription = "Questions", tint = Color.White)
            Text(text = "Otázky", style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold))

        }

    }

}


@Composable
fun Last7days(
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
            text = "Poslednych 7 dni",
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            Icon(painter = painterResource(id = R.drawable.mood_graph), contentDescription = "Last 7 Days Graph" )
        }
    }



}


@Composable
fun PriorityOfTheMonth(
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
            text = "Priorita na tento mesiac",
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                Icon(
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "Sleep",
                    tint = Color.White,
                )
//                Spacer(modifier = Modifier.width(8.dp)) // Adjust the spacing between icon and text
                Text(
                    text = "Spanok",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
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
            text = "Analyza tvojej nalady",
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                Icon(
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "",
                    tint = Color.White,
                )
//                Spacer(modifier = Modifier.width(8.dp)) // Adjust the spacing between icon and text
                Text(
                    text = "Analyza",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

