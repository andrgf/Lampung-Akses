package com.example.newsapp.presentation.onboarding.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.PurpleGrey40

@Composable
fun OnBoardingIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = PurpleGrey40
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) {page ->
            Box(modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingIndicatorPreview() {
    OnBoardingIndicator(pageSize = 3, selectedPage = 0)
}