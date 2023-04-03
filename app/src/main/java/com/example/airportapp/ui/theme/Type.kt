package com.example.airportapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.airportapp.R

val RedHat = FontFamily(
    Font(R.font.red_hat_regular, FontWeight.Normal),
    Font(R.font.rad_hat_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = RedHat,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 39.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    )
)