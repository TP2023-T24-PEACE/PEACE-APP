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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.t24.peaceapp.R
import kotlin.math.max


@Composable
fun PriorityOfTheWeek(
){

    var priority by remember { mutableStateOf("") }

    //val challenge = "Nastav si 1-hodinovú pauzu od všetkých digitálnych zariadení."
    var challenge by remember { mutableStateOf("") }

    fun selectChallenge(){
        challenge = "Vytvor si digitálnu detoxikáciu počas víkendov alebo vo večerných hodinách."
    }

    // This is the callback that the child will call
    val handleChildDataPriority: (String) -> Unit = { data ->
        priority = data
        selectChallenge()
    }


    var showDialogPriority by remember { mutableStateOf(false) }
    var showDialogChallenge by remember { mutableStateOf(false) }

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
                        .width(130.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (challenge != "") {
                        Image(
                            painter = painterResource(id = R.drawable.sleep),
                            modifier = Modifier.size(60.dp),
                            contentDescription = "Sleep"
                        )
                        Text(
                            text = (if(priority != "") priority else ""),
                            color = Color.White,
                            fontSize = 16.sp,
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
                                showDialogPriority = true
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
                    Text(
                        text = (if(challenge != "") challenge else "Aktuálne nemáš nastavenú žiadnu výzvu."),
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
    if (showDialogPriority) {
        MinimalDialog(onDismissRequest = {
            showDialogPriority = false
            showDialogChallenge = true
        },
            onDataChange = handleChildDataPriority,
        )
    }
    if(showDialogChallenge){
        MinimalDialogChallenge(onDismissRequest = {
            showDialogChallenge = false
        },
            priority = priority,
            challenge = challenge,
        )

    }
}

@Composable
fun MinimalDialogChallenge(onDismissRequest: () -> Unit, priority: String, challenge: String) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF7BD07F),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = priority,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Tvojou úlohou na tento týždeň je",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = challenge,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                Button(
                    contentPadding = PaddingValues(32.dp, 8.dp, 32.dp, 8.dp),
                    elevation = ButtonDefaults.buttonElevation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5CDB5C),
                        contentColor = Color.White),
                    onClick = {
                        onDismissRequest()
                    }) {
                    Text(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = "PRIJAŤ VÝZVU",
                        color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit, onDataChange: (String) -> Unit) {

    val goals = listOf(
        "Emočný stav",
        "Produktivita a práca/škola",
        "Osobné vzťahy",
        "Zdravie a pohoda",
        "Digitálne prostredie a média"
    )

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF7BD07F),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Vyber si cieľ na tento týždeň",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                SimpleFlowRow(
                    verticalGap = 8.dp,
                    horizontalGap = 8.dp,
                    alignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                ) {
                    for (goal in goals) {
                        Button(
                            onClick = {
                                // Set the selected goal in the state
                                onDismissRequest()
                                onDataChange(goal)
                                      },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF5CDB5C),
                                contentColor = Color.White)
                        ) {
                            Text(
                            text = goal,
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleFlowRow(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: @Composable () -> Unit
) = Layout(content, modifier) { measurables, constraints ->
    val hGapPx = horizontalGap.roundToPx()
    val vGapPx = verticalGap.roundToPx()

    val rows = mutableListOf<MeasuredRow>()
    val itemConstraints = constraints.copy(minWidth = 0)

    for (measurable in measurables) {
        val lastRow = rows.lastOrNull()
        val placeable = measurable.measure(itemConstraints)

        if (lastRow != null && lastRow.width + hGapPx + placeable.width <= constraints.maxWidth) {
            lastRow.items.add(placeable)
            lastRow.width += hGapPx + placeable.width
            lastRow.height = max(lastRow.height, placeable.height)
        } else {
            val nextRow = MeasuredRow(
                items = mutableListOf(placeable),
                width = placeable.width,
                height = placeable.height
            )

            rows.add(nextRow)
        }
    }

    val width = rows.maxOfOrNull { row -> row.width } ?: 0
    val height = rows.sumOf { row -> row.height } + max(vGapPx.times(rows.size - 1), 0)

    val coercedWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
    val coercedHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)

    layout(coercedWidth, coercedHeight) {
        var y = 0

        for (row in rows) {
            var x = when(alignment) {
                Alignment.Start -> 0
                Alignment.CenterHorizontally -> (coercedWidth - row.width) / 2
                Alignment.End -> coercedWidth - row.width

                else -> throw Exception("unsupported alignment")
            }

            for (item in row.items) {
                item.place(x, y)
                x += item.width + hGapPx
            }

            y += row.height + vGapPx
        }
    }
}

private data class MeasuredRow(
    val items: MutableList<Placeable>,
    var width: Int,
    var height: Int
)
