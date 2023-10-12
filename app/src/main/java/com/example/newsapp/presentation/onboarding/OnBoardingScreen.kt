package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.onboarding.component.OnBoarding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage) {
                    0 -> listOf("", "Selanjutnya")
                    1 -> listOf("Kembali", "Selanjutnya")
                    2 -> listOf("Kembali", "Mulai")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) {
            OnBoarding(page = pages[it])

        }
    }
}