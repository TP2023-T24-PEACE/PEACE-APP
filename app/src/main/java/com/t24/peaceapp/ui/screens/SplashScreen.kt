package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.t24.peaceapp.R
import com.t24.peaceapp.ui.screens.destinations.IntroductionScreenDestination


@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4C5F18), Color(0xFF2E9E6F)
                    ),
                    tileMode = TileMode.Repeated
                )
            ).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.015f))
        Image(
            painter = painterResource(id = R.drawable.relax),
            contentDescription = "Peace App Relax Image")
        Spacer(modifier = Modifier.weight(0.01f))
        Text(
            text = "Zhlboka sa nadýchni...",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.weight(0.01f))
        Text(
            text = "Víta ťa aplikácia",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge)
        Text(
            text = "PEACE", textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.weight(0.01f))
        Button(
            contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
            elevation = ButtonDefaults.buttonElevation(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5CDB5C),
                contentColor = Color.White),
            onClick = {
                navigator.navigate(IntroductionScreenDestination)
            }) {
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                text = "POKRAČOVAŤ",
                color = Color.White)
        }
        Spacer(
            modifier = Modifier.weight(0.01f)
        )
    }
}