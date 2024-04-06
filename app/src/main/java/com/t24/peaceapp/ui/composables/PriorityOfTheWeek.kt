package com.t24.peaceapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t24.peaceapp.R

@Composable
fun PriorityOfTheWeek(
){
    val challenge = "Nastav si 1-hodinovú pauzu od všetkých digitálnych zariadení."
    //val challenge = null


    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 0.dp)
            .fillMaxWidth()
    )
    {
        Text(
            text = "Priorita na tento týždeň",
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

                Column(
                    modifier = Modifier
                        // width 30% of the parent
                        .width(130.dp)
                ) {
                    if (challenge != null) {
                        Image(
                            painter = painterResource(id = R.drawable.sleep),
                            modifier = Modifier.size(100.dp),
                            contentDescription = "Sleep"
                        )
                        Text(
                            text = "Spánok",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Button(
                            contentPadding = PaddingValues(8.dp, 4.dp, 8.dp, 4.dp),
                            elevation = ButtonDefaults.buttonElevation(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF5CDB5C),
                                contentColor = Color.White),
                            onClick = {

                            }) {
                            Text(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                text = "ZVOLIŤ CIEĽ",
                                color = Color.White)
                        }

                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        // width 30% of the parent
                        .width(170.dp)
                ) {
                    Text(
                        text = "Úloha na dnes:",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    (if(challenge != null) challenge else "Aktuálne nemáš nastavenú žiadnu výzvu.")?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}