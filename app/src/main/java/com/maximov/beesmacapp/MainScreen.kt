package com.maximov.beesmacapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onNavigate: (String) -> Unit,
    onNavigateToCart: () -> Unit,
    cartItems: List<String>


) {
    val categories = listOf("Сумки", "Кошельки", "Ремни", "Брелки")
    //val cartItems = remember { mutableStateListOf<String>() }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bee_background1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            categories.forEach { category ->
                Button(
                    onClick = { onNavigate(category) },
                    colors = ButtonDefaults.buttonColors(
                        Color.Red
                        //backgroundColor = Color(0xFFFFA500) // оранжевый цвет для кнопок
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text(
                        text = category,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        Button(
            onClick = {onNavigateToCart()},
            modifier = Modifier.align(Alignment.TopEnd).padding(35.dp)
        ) {
            Text("Корзина (${cartItems.size})")
        }
    }
}