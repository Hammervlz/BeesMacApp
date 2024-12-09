package com.maximov.beesmacapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

data class OrderItem (val name: String, val quantity: Int, val price: Double )

@Composable
fun CheckoutScreen(onSubmit: () -> Unit, orderItem: List<OrderItem>) {
    val (name, setName) = remember { mutableStateOf("") }
    val (surname, setSurname) = remember { mutableStateOf("") }
    val (phoneNumber, setPhoneNumber) = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Введите свои данные", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = setName,
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = surname,
            onValueChange = setSurname,
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = setPhoneNumber,
            label = { Text("Телефон") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                sendOrderToTelegram(name, surname, phoneNumber, orderItem)
            }
            onSubmit()
        }) {
            Text("Отправить заказ")
        }
    }
}

suspend fun sendOrderToTelegram(name: String, surname: String, phoneNumber: String, orderItem: List<OrderItem>) {
    val httpClient = HttpClient(CIO)

    val chatId = "XXXXXXXXX" // Ваш chat_id
    val botToken = "XXXXXXXXXX:AAFp90SZ84GrrepynedGAz5nXquVqOuy6YE"
    val orderItem = orderItem.joinToString("\n") { "${it.name} - ${it.quantity} шт. по ${it.price} руб." }
    val message = """
        Новый заказ от $name $surname:
        Телефон: $phoneNumber
        Заказ: $orderItem
    """

    val telegramUrl = "https://api.telegram.org/bot$botToken/sendMessage"

    try {
        Log.d("Telegram", "Отправка сообщения: $message")
        val response = httpClient.post<Unit>(telegramUrl) {
            parameter("chat_id", chatId)
            parameter("text", message)
        }
        Log.d("Telegram", "Message sent successfully: $response")
    } catch (e: Exception) {
        Log.e("Telegram", "Error sending message", e)
    } finally {
        httpClient.close()
    }
}
