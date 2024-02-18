package com.t24.peaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StepIndicator(currentStep: Int, totalSteps: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        repeat(totalSteps) { index ->
            val dotColor = if (index < currentStep) Color(0xFF4DD543) else Color.White
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(color = dotColor, shape = CircleShape)
            )
            if (index < totalSteps - 1) {
                Spacer(modifier = Modifier.width(10.dp)) // Adjust spacing as needed
            }
        }
    }
}