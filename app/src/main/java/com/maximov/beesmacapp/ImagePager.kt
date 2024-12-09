package com.maximov.beesmacapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ImagePager(images: List<Int>, onClose: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Dialog(
        onDismissRequest = { onClose() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .size(400.dp)// Уменьшаем размер Card для лучшего отображения
                ) {
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(400.dp)// Уменьшаем размер Card для лучшего отображения
                    )
                }
            }
            Text(
                text = "Закрыть",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Red,
                modifier = Modifier
                    .clickable { onClose() }
                    .padding(8.dp, 0.dp, 0.dp, 70.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
