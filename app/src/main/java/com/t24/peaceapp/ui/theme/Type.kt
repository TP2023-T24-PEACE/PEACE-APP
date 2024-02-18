package com.t24.peaceapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.t24.peaceapp.R

// Set of Material typography styles to start with

var poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = White,
        shadow = Shadow(
            color = Color(0f,0f,0f,0.25f),
            offset = Offset(0f,4f),
            blurRadius = 4f
        )
    ),
    /* Other default text styles to override */
    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp,
        color = White,
        shadow = Shadow(
            color = Color(0f,0f,0f,0.25f),
            offset = Offset(0f,4f),
            blurRadius = 4f
        )
    ),
    titleMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.5.sp,
        color = White,
        shadow = Shadow(
            color = Color(0f,0f,0f,0.25f),
            offset = Offset(0f,4f),
            blurRadius = 4f
        )
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = White,
        shadow = Shadow(
            color = Color(0f,0f,0f,0.25f),
            offset = Offset(0f,4f),
            blurRadius = 4f
        )
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp,
        color = Color.White,
        shadow = Shadow(
            color = Color(0f,0f,0f,0.25f),
            offset = Offset(0f,4f),
            blurRadius = 4f
        )
    ),

)