package com.t24.peaceapp.ui.screens

import android.content.Context
import android.os.Build
import android.os.StrictMode
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberTopAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.CartesianChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.component.shape.shader.TopBottomShader
import com.patrykandpatrick.vico.core.model.CartesianChartModel
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.LineCartesianLayerModel
import com.patrykandpatrick.vico.core.model.lineSeries
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.DashboardDestination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking1Destination
import com.t24.peaceapp.ui.screens.destinations.Moodtracking3Destination
import com.t24.peaceapp.ui.state.UpdateUserToken
import com.t24.peaceapp.ui.state.context

import khttp.get as khttp_get
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.json.JSONObject



@RequiresApi(Build.VERSION_CODES.O)
@Destination
@Composable
fun AnalysisScreen(navigator: DestinationsNavigator) {


    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    val sharedPrefToken = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val token = sharedPrefToken.getString("token", "")

    val authorization = "Bearer"+token?.replace("\"", "")

    val headers = mapOf(
        "Content-Type" to "application/json",
        "X-Apikey" to "30fa4be8-f8bb-4131-80bb-eda62eb9d116",
        "Authorization" to authorization
    )

    val response =  khttp_get("https://tp-be-production.up.railway.app/api/v1/stats-mood-trend",
        headers = headers)

    if(response.statusCode != 200){
        navigator.navigate(DashboardDestination)
    }

    println(response.jsonObject.toString())
    val jsonObject = JSONObject(response.jsonObject.toString())

    val responseData = jsonObject.getJSONObject("response")
    val totalEntries = responseData.get("total_entries")
    val averageMoodLastWeek = responseData.get("average_mood_last_week").toString().toFloat()
    val averageMoodPrevMonth = responseData.get("average_mood_previous_month").toString().toFloat()
    val trendChange = responseData.get("trend_change").toString().toFloat()
    val trendMessage = responseData.get("trend_message")

    val entriesData = responseData.getJSONArray("entries_data")



    val grapgData = mutableMapOf<LocalDate, Float>()
    // Iterate over the JSON array
    for (i in 0 until entriesData.length()) {
        val temp = entriesData.getJSONObject(i)
        val day = LocalDate.parse(temp.getString("day"))
        val averageMood = temp.getDouble("average_mood").toFloat()*100
        grapgData[day] = averageMood
    }


    val responseStatsGood =  khttp_get("https://tp-be-production.up.railway.app/api/v1/stats-good",
        headers = headers)

    val responseStatsBad =  khttp_get("https://tp-be-production.up.railway.app/api/v1/stats-bad",
        headers = headers)


    if(responseStatsGood.statusCode != 200 || responseStatsBad.statusCode != 200){
        navigator.navigate(DashboardDestination)
    }


    val jsonObjectGood = JSONObject(responseStatsGood.jsonObject.toString())
    val jsonObjectBad = JSONObject(responseStatsBad.jsonObject.toString())
    val responseDataGood = jsonObjectGood.getJSONArray("items")
    val responseDataBad = jsonObjectBad.getJSONArray("items")


    var moodBest = ""
    var moodBestCount = 0
    var moodWorst = ""
    var moodWorstCount = 0

    if(responseDataGood.length() != 0){
        moodBest = responseDataGood.getJSONObject(0).getString("reason")
        moodBestCount = responseDataGood.getJSONObject(0).getString("count").toInt()
    }

    if( responseDataBad.length() != 0) {
        moodWorst = responseDataBad.getJSONObject(0).getString("reason")
        moodWorstCount = responseDataBad.getJSONObject(0).getString("count").toInt()
    }

    val tinder =  khttp_get("https://tp-be-production.up.railway.app/api/v1/stats-tinder-trend",
        headers = headers)

    if(tinder.statusCode != 200){
        navigator.navigate(DashboardDestination)
    }


    println(tinder.jsonObject.toString())
    val tinderResponse = JSONObject(tinder.jsonObject.toString())

    val tinderData = tinderResponse.getJSONObject("response")

    val tinderTotalEntries = tinderData.getString("total_entries")
    val beneficial = tinderData.getString("positive_responses")
    val harmful = tinderTotalEntries.toInt() - beneficial.toInt()
    val beneficialPercentage = tinderData.getString("positive_percentage")
    val tinderTrendChange = tinderData.getString("trend_change")
    val tinderTrendMessage = tinderData.getString("trend_message")




    val gradient = Brush.verticalGradient(
        0.0f to Color(0xBF4C5F18),
        1.0f to Color(0xBF2E9E6F),
        startY = 0.0f,
        endY = 1500.0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp) // Padding at the bottom to avoid overlap with the button
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Graf nálad",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Graph(grapgData)
                EntriesStats(totalEntries as Int, averageMoodLastWeek as Float, averageMoodPrevMonth as Float, trendChange as Float, trendMessage as String)
                MoodBestWorst(moodBest, moodBestCount, moodWorst, moodWorstCount)
                QuestionsStats(tinderTotalEntries.toInt(), beneficial.toInt(), harmful, beneficialPercentage.toFloat(), tinderTrendChange.toFloat(), tinderTrendMessage)
            }
        }


        Button(
            onClick = {
                navigator.navigate(DashboardDestination)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(15.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF5CDB5C))
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            elevation = ButtonDefaults.buttonElevation(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5CDB5C),
                contentColor = Color.White
            )
        ) {
            Text(
                "Domovská obrazovka",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Graph(data: MutableMap<LocalDate, Float> ) {

//    val data =
//        mapOf(
//            LocalDate.parse("2022-07-01") to 2f,
//            LocalDate.parse("2022-07-02") to 6f,
//            LocalDate.parse("2022-07-03") to 4f,
//            LocalDate.parse("2022-07-04") to 3f,
//            LocalDate.parse("2022-07-05") to 2f,
//            LocalDate.parse("2022-07-06") to 5f,
//            LocalDate.parse("2022-07-07") to 10f,
//        )


    println(data)
    val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDate>>()
    val xToDates = data.keys.associateBy { it.toEpochDay().toFloat() }

    val dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
    val horizontalAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, chartValues, _ ->
        (chartValues.model.extraStore[xToDateMapKey][x] ?: LocalDate.ofEpochDay(x.toLong()))
            .format(dateTimeFormatter)
    }

    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) { modelProducer.tryRunTransaction {
        lineSeries { series(xToDates.keys, data.values) }
        updateExtras { it[xToDateMapKey] = xToDates }
    } }


        CartesianChartHost(
            rememberCartesianChart(
                rememberLineCartesianLayer(
                    lines = listOf(
                        rememberLineSpec(
                            shader =
                            TopBottomShader(
                                DynamicShaders.color(Color(0xFFEEFF00)),
                                DynamicShaders.color(Color(0xFFFFFFFF)),
                            ),
                        )
                    )
                ),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = horizontalAxisValueFormatter
                ),

                ),
            modelProducer,
        )

}

@Composable
fun EntriesStats(total_entries: Int, average_mood_last_week: Float, average_mood_prev_month: Float, trend_change: Float, trend_message: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)

    ) {
        Column {
            Text("Záznamy", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Počet záznamov: $total_entries", color = Color.White, fontSize = 16.sp)
            Text("Priemerná nálada v minulom týždni: $average_mood_last_week", color = Color.White, fontSize = 16.sp)
            Text("Priemerná nálada v minulom mesiaci: $average_mood_prev_month", color = Color.White, fontSize = 16.sp)
            Text("Zmena trendu: $trend_change", color = Color.White, fontSize = 16.sp)
            Text(trend_message, color = Color.White, fontSize = 16.sp)
        }
    }

}

@Composable
fun MoodBestWorst(good: String, goodCount: Int, bad: String, badCount: Int){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column {
            Text("Najčastejší dôvod dobrej nálady: $good (${goodCount}x)", color = Color.White, fontSize = 16.sp)
            Text("Najčastejší dôvod zlej nálady: $bad (${badCount}x)", color = Color.White, fontSize = 16.sp)
        }
    }
}


@Composable
fun QuestionsStats(totalEntrie: Int, positiveResponses: Int, negativeResponses: Int, beneficialPercentage: Float, trendChange: Float, trendMessage: String){

    Column {
        Text("Otázky", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Počet záznamov: $totalEntrie", color = Color.White, fontSize = 16.sp)
        Text("Počet pozitívnych odpovedí: $positiveResponses", color = Color.White, fontSize = 16.sp)
        Text("Počet negatívnych odpovedí: $negativeResponses", color = Color.White, fontSize = 16.sp)
        Text("Pomer pozitívnych odpovedí: ${beneficialPercentage}%", color = Color.White, fontSize = 16.sp)
        Text("Zmena trendu: $trendChange", color = Color.White, fontSize = 16.sp)
        Text(trendMessage, color = Color.White, fontSize = 16.sp)
    }

}