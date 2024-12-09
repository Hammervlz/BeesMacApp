package com.maximov.beesmacapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductItem(product: Product) {
    val Orange = Color(0xFFFFA500)
    val Bolot = Color(0xffe35209)
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = product.name,
            color = Bolot,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = "Цена: ${product.price} руб.",
            color = Color.Yellow,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )

    }
}
