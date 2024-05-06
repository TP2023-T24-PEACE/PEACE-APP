package com.t24.peaceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t24.peaceapp.Mood

@Composable
fun MoodReason(
    mood: Mood,
    onValueChanged: (Boolean) -> Unit
) {

    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) Color(0xFF64AD41) else Color(0xFF94CC79)
            )
            .padding(15.dp)
            .clickable {
                isSelected = !isSelected
                onValueChanged(isSelected)
            }
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(id = mood.iconId),
            contentDescription = mood.title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(50.dp)
                .padding(bottom = 4.dp)
        )
        Text(
            text = mood.title,
            modifier = Modifier.align(
                Alignment.BottomCenter),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}
