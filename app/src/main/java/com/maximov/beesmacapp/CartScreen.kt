package com.maximov.beesmacapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen (
    cartItems: List<Product>,
    onBack: () -> Unit,
    onRemoveFromCart: (Product) -> Unit,
    onCheckout: () -> Unit
) {
    Column (modifier = Modifier.fillMaxSize().padding(25.dp)) {
        Spacer(modifier = Modifier.height(25.dp))
        Text("Корзина", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        cartItems.forEach { item ->
            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                 ProductItem(product = item) // Заменяем на использование компонента ProductItem
                Button(onClick = {onRemoveFromCart(item)},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text(text = "Удалить")
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onCheckout() }) {
            Text(text = "Оформить заказ")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {onBack()}) {
            Text(text = "Назад")
        }
    }
}
