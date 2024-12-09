package com.maximov.beesmacapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun KeychainScreen(onAddToCart: (Product) -> Unit, onBack: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val (selectedImages, setSelectedImages) = remember { mutableStateOf<List<Int>?>(null) }

    Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Image(
            painter = painterResource(id = R.drawable.bee_background_keychain),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )



        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(ProductsKeychain) { item ->
                Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    setSelectedImages(listOf(item.imageRes) + item.additionalImages)
                                    setShowDialog(true)
                                }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            ProductItem(product = item) // Заменяем на использование компонента ProductItem
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            onAddToCart(item)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("${item.name} добавлен в корзину по цене ${item.price} руб")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Добавить в корзину")
                    }
                }
            }
        }

        // Всплывающее уведомление
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(80.dp)
        )

        // Кнопка назад внизу экрана
        Button(
            onClick = { onBack() },
            modifier = Modifier.align(Alignment.BottomCenter).padding(50.dp)
        ) {
            Text("Назад")
        }

        // Диалог увеличенных изображений
        if (showDialog && selectedImages != null) {
            ImagePager(images = selectedImages) { setShowDialog(false) }
        }
    }
}
