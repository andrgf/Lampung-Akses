package com.example.newsapp.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.ui.theme.WhiteGray
import com.example.newsapp.util.dateFormat

fun Modifier.shimmerEffect() = composed { 
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = WhiteGray)
}

@Composable
fun ArticleShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(2f)
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(15.dp)
                    .padding(bottom = 6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .padding(bottom = 6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .shimmerEffect(),

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .padding(bottom = 6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(top = 6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .shimmerEffect()
            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ArticleShimmerEffect()
}