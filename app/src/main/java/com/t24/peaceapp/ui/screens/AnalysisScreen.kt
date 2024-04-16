package com.t24.peaceapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AnalysisScreen {
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination
@Composable
fun AnalysisScreen(navigator: DestinationsNavigator) {
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
                Skuska()

            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Skuska() {

//    val modelProducer = remember { CartesianChartModelProducer.build() }
//    LaunchedEffect(Unit) { modelProducer.tryRunTransaction { lineSeries {
//
//        series(x = listOf("2022-07-01", "2022-07-02", "2022-07-03", "2022-07-04"), y = listOf(2, 5, 3, 4))
//    } } }
//    CartesianChartHost(
//        rememberCartesianChart(
//            rememberLineCartesianLayer(),
//            startAxis = rememberStartAxis(),
//            bottomAxis = rememberBottomAxis(),
//        ),
//        modelProducer,
//    )

    val data =
        mapOf(
            LocalDate.parse("2022-07-01") to 2f,
            LocalDate.parse("2022-07-02") to 6f,
            LocalDate.parse("2022-07-03") to 4f,
            LocalDate.parse("2022-07-04") to 3f,
            LocalDate.parse("2022-07-05") to 2f,
            LocalDate.parse("2022-07-06") to 5f,
        )
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
            )),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                valueFormatter = horizontalAxisValueFormatter
            ),

        ),
        modelProducer,
    )



}

//@Composable
//fun skuska() {
//    val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)
//
//    Chart(
//        chart = columnChart(),
//        model = chartEntryModel,
//        startAxis = rememberStartAxis(),
//        bottomAxis = rememberBottomAxis(),
//    )
//
//
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun skuska2() {
//    val chartEntryModel = entryModelOf(4f, 12f, 8f)
//    val data = listOf("2022-07-01" to 2f, "2022-07-02" to 6f, "2022-07-04" to 4f)
//        .associate { (dateString, yValue) ->
//        LocalDate.parse(dateString) to yValue
//    }
//    val dates = listOf("2022-07-01", "2022-07-02", "2022-07-04")
//    println(data)
//
//    val xValuesToDates = data.keys.associateBy { it.toEpochDay().toFloat() }
//    println("xValuesToDates:")
//    println(xValuesToDates)
//
//
//    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
//    val horizontalAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
//        println("value: $value  xValuesToDates[value]: ${xValuesToDates[value]}")
//        (xValuesToDates[value] ?: LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter)
//    }
//    Chart(
//        chart = lineChart(),
//        model = chartEntryModel,
//        startAxis = rememberStartAxis(),
//        bottomAxis = rememberBottomAxis(
//            valueFormatter = horizontalAxisValueFormatter
//        ),
//
//
//    )
//
//}
