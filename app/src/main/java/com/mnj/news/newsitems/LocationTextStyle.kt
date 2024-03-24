package com.mnj.news.newsitems

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object LocationTextStyle {

    val style = TextStyle(
        color = Color.DarkGray,
        fontSize = 22.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 1.sp,
        textAlign = TextAlign.Center,
        )

    val valueSpanStyle = SpanStyle(
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 1.sp
    )

    val keySpanStyle = SpanStyle(
        color = Color.Gray,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif,
    )
}